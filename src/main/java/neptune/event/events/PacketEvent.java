package neptune.event.events;

import neptune.event.Event;
import net.minecraft.network.packet.Packet;

public class PacketEvent extends Event {
    Packet<?> packet;

    public static class Recieved extends PacketEvent {
        public Recieved(Packet<?> packet) {
        }
    }
    public static class Sent extends PacketEvent {
        public Sent(Packet<?> packet) {

        }
    }
}
