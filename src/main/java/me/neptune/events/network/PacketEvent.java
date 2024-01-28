package me.neptune.events.network;

import me.neptune.api.event.Event;
import net.minecraft.network.packet.Packet;

/**
 * Simple packetevent.
 * Probably as simple as it gets tbh but I cba to write an actual listener/subscriber
 * system and shit at which a good packetevent would be useful.
 */
public class PacketEvent extends Event {
    private final Packet<?> packet;
    public PacketEvent(Packet<?> packetIn) {
        this.packet = packetIn;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public static class Send extends PacketEvent {
        public Send(Packet<?> packet) {
            super(packet);
        }
    }

    public static class Receive extends PacketEvent {
        public Receive(Packet<?> packet) {
            super(packet);
        }
    }
}
