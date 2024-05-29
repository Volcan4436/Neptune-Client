package neptune.utils;

import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.network.packet.Packet;

import static neptune.utils.MinecraftInterface.mc;

public class NetworkUtils {

    // TODO: sendPacket with repeat and delayMillis

    public static int getPing() {
        if (mc.getNetworkHandler() == null || mc.player == null) return 0;
        PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
        if (playerListEntry == null) return 0;
        return playerListEntry.getLatency();
    }

    public static void sendPacket(Packet<?> packet) {
        mc.getNetworkHandler().sendPacket(packet);
    }

    public static void sendPacket(Packet<?> packet, long delayMillis) {
        // TODO: Implement
        mc.getNetworkHandler().sendPacket(packet);
    }

    public static void sendPacket(Packet<?> packet, int repeat) {
        for (int i = 0; i < repeat; i++)
            mc.getNetworkHandler().sendPacket(packet);
    }
}
