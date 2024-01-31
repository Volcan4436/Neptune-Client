package neptune.features.misc;

import com.google.common.eventbus.Subscribe;
import neptune.event.events.PacketEvent;
import neptune.mixins.PlayerPositionLookS2CPacketAccessor;
import neptune.module.Mod;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;

public class NoRotate extends Mod {

    public Packet<?> packet;
    public NoRotate() {
        super("NoRotate", "Cancels Rotations", Category.MISC);
    }

    @Subscribe
    private void onReceivePacket(PacketEvent.Receive packetEvent) {
        if (PacketEvent.packet instanceof PlayerPositionLookS2CPacket) {
            assert mc.player != null;
            ((PlayerPositionLookS2CPacketAccessor) PacketEvent.packet1).setPitch(mc.player.getPitch());
            ((PlayerPositionLookS2CPacketAccessor) PacketEvent.packet1).setYaw(mc.player.getYaw());
        }
    }




}
