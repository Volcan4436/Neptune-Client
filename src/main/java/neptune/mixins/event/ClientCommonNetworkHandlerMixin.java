package neptune.mixins.event;

import neptune.event.impl.network.PacketEvent;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonNetworkHandler.class)
public class ClientCommonNetworkHandlerMixin {
    @Unique
    private final PacketEvent event = new PacketEvent(NetworkSide.SERVERBOUND);

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    public void sendPacket(Packet<?> packet, CallbackInfo ci) {
        event.setPacket(packet);
        if (event.call().isCancelled())
            ci.cancel();
    }
}