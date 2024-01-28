package me.neptune.asm.mixins.client;

import me.neptune.Neptune;
import me.neptune.modules.client.Globals;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MixinMinecraftClient
{
    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    public void windowTitleHook(CallbackInfoReturnable<String> cir)
    {
        if (Globals.getInstance().windowTitle.getValue())
            cir.setReturnValue(Neptune.NAME + " - " + Neptune.VER_STRING);
    }
}
