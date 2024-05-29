package neptune;

import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import neptune.command.CommandManager;
import neptune.module.api.Mod;
import neptune.module.ModuleManager;
import neptune.utils.MinecraftInterface;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class Neptune implements ModInitializer, MinecraftInterface {
    public static final Neptune INSTANCE = new Neptune();

    private final Logger logger = LogManager.getLogger(Neptune.class);
    public final IEventBus EVENT_BUS = new EventBus();
    private final CommandManager commandManager;
    private final ModuleManager moduleManager;

    public Neptune() {
        EVENT_BUS.registerLambdaFactory("neptune", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();
    }

    @Override
    public void onInitialize() {
        logger.info("Neptune Client is starting");
        logger.info("Neptune Client by heedi has finished loading!");
    }

    public void onTick() {
        if (mc.player != null) {
            for (Mod module : moduleManager.getEnabledModules()) {
                module.onTick();
            }
        }
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static Neptune getInstance() {
        return INSTANCE;
    }
}
