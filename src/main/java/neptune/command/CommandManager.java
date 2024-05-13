package neptune.command;

import neptune.command.impl.Bind;
import neptune.command.impl.Help;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public static CommandManager INSTANCE = new CommandManager();
    public final List<Command> cmds = new ArrayList<>();
    private final String commandPrefix = "+";

    public CommandManager() {
        add(new Bind());
        add(new Help());
    }

    public void add(Command command) {
        if (!cmds.contains(command)) {
            cmds.add(command);
        }
    }

    public void remove(Command command) {
        cmds.remove(command);
    }

    public List<Command> getCmds() {
        return cmds;
    }

    public String getCommandPrefix() {
        return commandPrefix;
    }
}

