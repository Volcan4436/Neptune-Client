package me.neptune.asm.mixins.network;

import io.netty.channel.ChannelHandlerContext;
import me.neptune.Neptune;
import me.neptune.events.network.PacketEvent;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class MixinClientConnection
{
    @Inject(method = "sendImmediately", at = @At("HEAD"), cancellable = true)
    public void onSendPacketHook(Packet<?> packet, PacketCallbacks callbacks, CallbackInfo ci) {
        PacketEvent.Send event = new PacketEvent.Send(packet);
        Neptune.EVENT_BUS.post(event);

        if(event.isCancelled())
            ci.cancel();
    }

    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V",
            at = @At("HEAD"), cancellable = true)
    public void onReceivedPacketHook(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo ci)
    {
        PacketEvent.Receive event = new PacketEvent.Receive(packet);
        Neptune.EVENT_BUS.post(event);

        if(event.isCancelled())
            ci.cancel();
    }
}
