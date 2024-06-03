package neptune;

import neptune.command.CommandManager;
import neptune.event.api.EventAPI;
import neptune.module.ModuleManager;
import neptune.utils.LoggerUtils;
import neptune.utils.MinecraftInterface;
import net.fabricmc.api.ClientModInitializer;

public class Neptune implements ClientModInitializer, MinecraftInterface {
    public static final Neptune INSTANCE = new Neptune();
    public static final String VERSION = "b0.2.0 - Beta";

    private final CommandManager commandManager;
    private final ModuleManager moduleManager;

    public Neptune() {
        EventAPI.register(this);
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();
    }

    @Override
    public void onInitializeClient() {
        LoggerUtils.logger.info("Initializing...");
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}