package neptune.utils;

import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.packet.Packet;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static neptune.utils.MinecraftInterface.mc;

public class NetworkUtils {
    private static final ScheduledExecutorService EXECUTOR = Executors.newScheduledThreadPool(1);

    public static int getPing() {
        if (mc.getNetworkHandler() == null || mc.player == null)
            return 0;

        PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());

        if (playerListEntry == null)
            return 0;

        return playerListEntry.getLatency();
    }

    public static void sendPacket(Packet<?> packet) {
        mc.getNetworkHandler().sendPacket(packet);
    }

    public static void sendPacket(Packet<?> packet, long delayMillis) {
        EXECUTOR.schedule(() -> mc.getNetworkHandler().sendPacket(packet), delayMillis, TimeUnit.MILLISECONDS);
    }

    public static void sendPacket(Packet<?> packet, int repeat) {
        for (int i = 0; i < repeat; i++)
            mc.getNetworkHandler().sendPacket(packet);
    }

    public static void sendPacket(Packet<?> packet, int repeat, long delayMillis) {
        for (int i = 0; i < repeat; i++)
            EXECUTOR.schedule(() -> mc.getNetworkHandler().sendPacket(packet), delayMillis, TimeUnit.MILLISECONDS);
    }
}