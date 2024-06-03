package neptune.mixins.event;

import neptune.event.api.EventState;
import neptune.event.impl.game.TickEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Unique
    private final TickEvent tickEvent = new TickEvent();

    @Inject(method = "tick", at = @At("HEAD"))
    public void tickPre(CallbackInfo ci) {
        tickEvent.setState(EventState.PRE);
        tickEvent.call();
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tickPost(CallbackInfo ci) {
        tickEvent.setState(EventState.POST);
        tickEvent.call();
    }
}