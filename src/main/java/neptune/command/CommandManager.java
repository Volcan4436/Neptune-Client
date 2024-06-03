package neptune.command;

import neptune.Neptune;
import neptune.command.api.Command;
import neptune.command.impl.*;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    public final List<Command> commands = new ArrayList<>();
    private char prefix = '+';

    public CommandManager() {
        commands.add(new Help());
        commands.add(new Clear());
        commands.add(new Name());
        commands.add(new Prefix());
        commands.add(new Bind());
    }

    public List<Command> getCommands() {
        return commands;
    }

    public char getPrefix() {
        return prefix;
    }

    public void setPrefix(char prefix) {
        this.prefix = prefix;
    }

    public static CommandManager getInstance() {
        return Neptune.INSTANCE.getCommandManager();
    }
}