package neptune.command.api;

import neptune.Neptune;
import neptune.command.CommandManager;
import neptune.utils.MinecraftInterface;

public abstract class Command implements MinecraftInterface {
    private final CommandInfo info = getClass().getAnnotation(CommandInfo.class);
    private final String name = info.name().isEmpty() ?
            getClass().getSimpleName()
            : info.name();
    private final String description = info.description();
    private final String[] aliases = info.aliases();

    public abstract void onExecute(String message, String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public static CommandManager getInstance() {
        return Neptune.INSTANCE.getCommandManager();
    }
}