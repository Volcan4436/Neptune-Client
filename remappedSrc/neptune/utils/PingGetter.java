package neptune.utils;

import net.minecraft.client.network.PlayerListEntry;

import static neptune.utils.MinecraftInterface.mc;

public class PingGetter {

    public static int getPing() {
        if (mc.getNetworkHandler() == null || mc.player == null) return 0;
        PlayerListEntry playerListEntry = mc.getNetworkHandler().getPlayerListEntry(mc.player.getUuid());
        if (playerListEntry == null) return 0;
        return playerListEntry.getLatency();
    }

}
