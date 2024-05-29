package neptune.mixins;

import neptune.Neptune;
import neptune.event.events.PacketSendEvent;
import net.minecraft.client.network.ClientCommonNetworkHandler;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientCommonNetworkHandler.class)
public class MixinClientCommonNetworkHandler {

    @Inject(method = "sendPacket", at = @At("HEAD"), cancellable = true)
    public void send(Packet<?> packet, CallbackInfo ci) {
        PacketSendEvent event = new PacketSendEvent(packet);
        Neptune.getInstance().EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
