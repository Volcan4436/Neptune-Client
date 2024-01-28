package me.neptune.managers;

import me.neptune.Neptune;
import me.neptune.managers.impl.client.CommandManager;
import me.neptune.managers.impl.client.ModuleManager;

public class ManagerHelper
{
    public static final ModuleManager MODULES = new ModuleManager();
    public static final CommandManager COMMANDS = new CommandManager();
    public static void init()
    {
        Neptune.getLogger().info("Initializing modules.");
        MODULES.init();
        Neptune.getLogger().info("Initializing commands.");
        COMMANDS.init();
    }
}
