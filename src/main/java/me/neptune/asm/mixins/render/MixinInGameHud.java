package me.neptune.asm.mixins.render;

import me.neptune.Neptune;
import me.neptune.events.render.Render2DEvent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud
{
    @Inject(method = "render", at = @At("HEAD"))
    public void renderHook(DrawContext context, float tickDelta, CallbackInfo ci) {
        Neptune.EVENT_BUS.post(new Render2DEvent(context, tickDelta));
    }
}
