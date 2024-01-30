package neptune;

import neptune.command.CommandManager;
import neptune.event.EventManager;
import neptune.module.Mod;
import neptune.module.ModuleManager;
import neptune.ui.screens.clickgui.ClickGUI;
import neptune.utils.MinecraftInterface;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class Client implements ModInitializer, MinecraftInterface {

    public static Client instance;
    private final Logger logger = LogManager.getLogger(Client.class);
    private final EventManager eventManager;
    private final CommandManager commandManager;
    private final ModuleManager moduleManager;

    public Client() {
        this.eventManager = new EventManager();
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();
    }

    @Override
    public void onInitialize() {
        logger.info("Neptune Client is starting");
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }
}
