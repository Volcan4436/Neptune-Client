package neptune.mixins;

import neptune.module.ModuleManager;
import neptune.module.api.Module;
import neptune.module.impl.visuals.FullBright;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
    @Unique
    private final Module fullBright = ModuleManager.getInstance().getModule(FullBright.class);

    @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/texture/NativeImage;setColor(III)V"))
    public void update(Args args) {
        if (fullBright.isToggled())
            args.set(2, 0xFFFFFFFF);
    }
}