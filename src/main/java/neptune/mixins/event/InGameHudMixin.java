package neptune.mixins.event;

import neptune.event.api.EventState;
import neptune.event.impl.render.RenderEvent;
import neptune.event.impl.render.RenderingLayer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Unique
    private final RenderEvent event = new RenderEvent(RenderingLayer.TWO_DIMENSIONAL);

    @Inject(method = "render", at = @At("HEAD"))
    public void renderPre(DrawContext context, float tickDelta, CallbackInfo ci) {
        event.update(EventState.PRE, context, tickDelta);
        event.call();
    }

    @Inject(method = "render", at = @At("TAIL"))
    public void renderPost(DrawContext context, float tickDelta, CallbackInfo ci) {
        event.update(EventState.POST, context, tickDelta);
        event.call();
    }
}