package neptune;

import neptune.Module.Mod;
import neptune.Module.ModuleManager;
import neptune.ui.screens.clickgui.ClickGUI;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class Client implements ModInitializer {
    public static final Client INSTANCE = new Client();
    public Logger logger = LogManager.getLogger(Client.class);

    private MinecraftClient mc = MinecraftClient.getInstance();

    private static final String commandPrefix = ".";
    public static String getCommandPrefix() {
        return commandPrefix;
    }
    @Override
    public void onInitialize() {
        logger.info("Neptune Client is starting");
    }

    public void onKeypress(int key, int action) {
        if (mc.currentScreen == null) {
            if (action == GLFW.GLFW_PRESS) {
                for (Mod module : ModuleManager.INSTANCE.getModules()) {
                    if (key == module.getKey()) module.toggle();
                }
                if (key == GLFW.GLFW_KEY_RIGHT_SHIFT) mc.setScreen(ClickGUI.INSTANCE);
            }
        }
    }
    public void onTick() {
        if (mc.player != null) {
            for (Mod module : ModuleManager.INSTANCE.getEnabledModules()) {
                module.onTick();
            }
        }
    }
}
