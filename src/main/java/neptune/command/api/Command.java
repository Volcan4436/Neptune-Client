package neptune.command.api;

import neptune.Neptune;
import neptune.command.CommandManager;
import neptune.utils.ChatUtils;
import neptune.utils.MinecraftInterface;
import net.minecraft.util.Formatting;

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

    protected void error(String errorType, String correctUsage) {
        ChatUtils.messagefBranding("%sInvalid %s, Please use: %s ", Formatting.RED, errorType, correctUsage);
    }
}