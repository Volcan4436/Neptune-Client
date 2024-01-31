package neptune.features.client;

import com.google.common.eventbus.Subscribe;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import neptune.event.events.GameJoinedEvent;
import neptune.event.events.PacketEvent;
import neptune.module.Mod;
import neptune.module.settings.BooleanSetting;
import neptune.utils.player.ChatUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.util.Formatting;

import java.util.Random;
import java.util.UUID;

public class Notifications extends Mod {

    private final Random random = new Random();
    private final Object2IntMap<UUID> totemPopMap = new Object2IntOpenHashMap<>();
    private final Object2IntMap<UUID> chatIdMap = new Object2IntOpenHashMap<>();

    private final BooleanSetting popCounter = new BooleanSetting("PopCounter", false);

    public Notifications() {
        super("Notifications", "Notifies you of certain things", Category.CLIENT);
    }

    private int getChatId(Entity entity) {
        return chatIdMap.computeIfAbsent(entity.getUuid(), value -> random.nextInt());
    }

    @Override
    public boolean onEnable() {
        if (popCounter.isEnabled()) {
            totemPopMap.clear();
            chatIdMap.clear();
        }
        return false;
    }

    @Subscribe
    private void onGameJoin(GameJoinedEvent event) {
        if (popCounter.isEnabled()) {
            totemPopMap.clear();
            chatIdMap.clear();
        }
    }

    @Subscribe
    private void onReceivePacket(PacketEvent.Receive packetEvent) {
        if (popCounter.isEnabled()) {
            if (!(packetEvent.packet instanceof EntityStatusS2CPacket packet)) return;

            if (packet.getStatus() != 35) return;

            Entity entity = packet.getEntity(mc.world);

            if (!(entity instanceof PlayerEntity player)) return;

            synchronized (totemPopMap) {
                int pops = totemPopMap.getOrDefault(player.getUuid(), 0);
                totemPopMap.put(player.getUuid(), ++pops);

                ChatUtils.sendMsg(getChatId(entity), Formatting.YELLOW, "(highlight)%s(default) popped (highlight)%d(default) %s.", player.getName(), pops, pops == 1 ? "totem" : "totems");
            }
        }
    }

    @Override
    public void onDisable() {

    }
}
