package neptune.mixins;

import neptune.Neptune;
import neptune.module.Mod;
import neptune.ui.screens.clickgui.ClickGUI;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//Do Not touch this or anything in the Events folder potatoman
// Just did what you going to do abt -L4J >:)
@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Shadow @Final private MinecraftClient client;

    @Inject(method = "onKey", at = @At("HEAD"))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci){
        if (client.currentScreen == null) {
            if (action == GLFW.GLFW_PRESS) {
                for (Mod module : Neptune.getInstance().getModuleManager().getModules()) {
                    if (key == module.getKey()) module.toggle();
                }
                //why are you hard coding this in make the fucking clickgui rebindable
                if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) client.setScreen(ClickGUI.INSTANCE);
            }
        }
    }
}
