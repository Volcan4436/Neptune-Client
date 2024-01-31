package neptune;

import neptune.command.CommandManager;
import neptune.event.EventManager;
import neptune.managers.ModuleManager;
import neptune.utils.MinecraftInterface;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Neptune implements ModInitializer, MinecraftInterface {

    public static Neptune instance;
    private final Logger logger = LogManager.getLogger(Neptune.class);
    private final EventManager eventManager;
    private final CommandManager commandManager;
    private final ModuleManager moduleManager;

    public Neptune() {
        this.eventManager = new EventManager();
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();
    }

    @Override
    public void onInitialize() {
        logger.info("[NEPTUNE] Neptune Client is starting by heedi");
        logger.info("[NEPTUNE] Neptune Client by heedi has finished loading!");
        System.out.println(getModuleManager().getModules());
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

    public static Neptune getInstance() {
        if (instance == null) {
            instance = new Neptune();
        }
        return instance;
    }
}
