package neptune.mixins;

import neptune.Neptune;
import neptune.event.events.TickEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Do Not touch this or anything in the Events folder potatoman
//Touched it what u going to do abt it >:) -L4J
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void onUpdate(CallbackInfo ci) {
        Neptune.getInstance().getEventManager().triggerEvent(new TickEvent());
    }
}
