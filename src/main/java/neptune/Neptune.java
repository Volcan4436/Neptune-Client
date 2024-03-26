package neptune;

import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.IEventBus;
import neptune.command.CommandManager;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.module.ModuleManager;
import neptune.utils.MinecraftInterface;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class Neptune implements ModInitializer, MinecraftInterface {

    public static final Neptune INSTANCE = new Neptune();

    public static Neptune instance;
    private final Logger logger = LogManager.getLogger(Neptune.class);
    public final IEventBus EVENT_BUS = new EventBus();
    private final CommandManager commandManager;
    private final ModuleManager moduleManager;
    public static float tickTimer = 1f;

    public Neptune() {
        EVENT_BUS.registerLambdaFactory("neptune", (lookupInMethod, klass) -> (MethodHandles.Lookup) lookupInMethod.invoke(null, klass, MethodHandles.lookup()));
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();
    }

    @Override
    public void onInitialize() {
        logger.info("[NEPTUNE] Neptune Client is starting");
        logger.info("[NEPTUNE] Neptune Client by heedi has finished loading!");
        System.out.println(getModuleManager().getModules());
    }

    public void onTick() {
        if (mc.player != null) {
            for (Mod module : ModuleManager.INSTANCE.getEnabledModules()) {
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
        if (instance == null) {
            instance = new Neptune();
        }
        return instance;
    }
}
