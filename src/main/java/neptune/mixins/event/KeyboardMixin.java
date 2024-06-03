package neptune.mixins.event;

import neptune.event.impl.input.KeyboardEvent;
import neptune.module.ModuleManager;
import neptune.module.api.Module;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Shadow @Final private MinecraftClient client;

    @Unique
    private final KeyboardEvent event = new KeyboardEvent();

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (client.currentScreen != null)
            return;

        event.setKey(key);
        event.setAction(action);
        if (event.call().isCancelled())
            ci.cancel();

        if (action != GLFW.GLFW_PRESS)
            return;

        // TODO: Probably do this in event
        ModuleManager.getInstance().getModules().stream()
                .filter(m -> m.getKey() == key)
                .forEach(Module::toggle);
    }
}