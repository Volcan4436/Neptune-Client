package neptune.module.impl.visuals;

import io.github.nevalackin.radbus.Listen;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import neptune.event.impl.network.PacketEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.BooleanSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;

import java.util.Random;
import java.util.UUID;

@ModuleInfo(description = "Notifies you of certain things")
public class Notifications extends Module {
    private final Object2IntMap<UUID> totemPopMap = new Object2IntOpenHashMap<>();
    private final Object2IntMap<UUID> chatIdMap = new Object2IntOpenHashMap<>();
    private final Random random = new Random();

    private final BooleanSetting popCounter = new BooleanSetting("PopCounter");

    public Notifications() {
        addSettings(popCounter);
    }

    private int getChatId(Entity entity) {
        return chatIdMap.computeIfAbsent(entity.getUuid(), value -> random.nextInt());
    }

    @Override
    public void onEnable() {
        if (popCounter.isEnabled()) {
            totemPopMap.clear();
            chatIdMap.clear();
        }
    }

    /*@Subscribe
    private void onGameJoin(GameJoinedEvent event) {
        if (popCounter.isEnabled()) {
            totemPopMap.clear();
            chatIdMap.clear();
        }
    }*/

    @Listen
    public void onPacket(PacketEvent event) {
        if (!popCounter.isEnabled())
            return;

        if (!event.isOutgoing())
            return;

        if (!(event.getPacket() instanceof EntityStatusS2CPacket packet))
            return;

        if (packet.getStatus() != 35)
            return;

        Entity entity = packet.getEntity(mc.world);
        if (!(packet.getEntity(mc.world) instanceof PlayerEntity player))
            return;

        synchronized (totemPopMap) {
            int pops = totemPopMap.getOrDefault(player.getUuid(), 0);
            totemPopMap.put(player.getUuid(), ++pops);

            //ChatUtils2.sendMsg(getChatId(entity), Formatting.YELLOW, "(highlight)%s(default) popped (highlight)%d(default) %s.", player.getName(), pops, pops == 1 ? "totem" : "totems");
        }
    }
}