package neptune.event.events;

import neptune.event.Event;
import net.minecraft.network.packet.Packet;

public class PacketSendEvent extends Event {

    private Packet<?> packet;

    public PacketSendEvent(Packet<?> packet){
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return this.packet;
    }

}
