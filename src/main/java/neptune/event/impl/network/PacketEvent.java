package neptune.event.impl.network;

import neptune.event.api.Event;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.packet.Packet;

public class PacketEvent extends Event {
    private final NetworkSide direction;
    private Packet<?> packet;

    public PacketEvent(NetworkSide direction) {
        this.direction = direction;
    }

    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public NetworkSide getDirection() {
        return direction;
    }

    public boolean isOutgoing() {
        return direction == NetworkSide.SERVERBOUND;
    }

    public boolean isIncoming() {
        return direction == NetworkSide.CLIENTBOUND;
    }
}