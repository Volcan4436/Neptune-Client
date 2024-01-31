package neptune.event.events;

import neptune.event.Event;
import net.minecraft.network.packet.Packet;

public class PacketEvent extends Event {
    public static Packet<?> packet;
    public static Packet<?> packet1;

    public static class Receive extends PacketEvent {
        private static final Receive INSTANCE = new Receive();

        public static Receive get(Packet<?> packet) {
            INSTANCE.setCancelled(false);
            INSTANCE.packet = packet;
            return INSTANCE;
        }
    }
    public static class Sent extends PacketEvent {
        public Sent(Packet<?> packet) {

        }
    }
}
