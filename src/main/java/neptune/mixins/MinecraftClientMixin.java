package neptune.mixins;

import neptune.Client;
import neptune.Event.EventUpdate;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Do Not touch this or anything in the Events folder potatoman
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void onUpdate(CallbackInfo ci) {
        new EventUpdate().call();
    }

    @Inject(at = @At("HEAD"), method = "tick")
    public void onTick(CallbackInfo ci) {
        Client.INSTANCE.onTick();
    }
}
