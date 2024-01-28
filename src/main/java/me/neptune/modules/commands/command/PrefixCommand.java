package me.neptune.modules.commands.command;

import me.neptune.Neptune;
import me.neptune.modules.commands.Command;
import net.minecraft.util.Formatting;

public class PrefixCommand
        extends Command {
    public PrefixCommand() {
        super("prefix", new String[]{"<char>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            Command.sendMessage(Formatting.GREEN + "Current prefix is " + Neptune.commandManager.getPrefix());
            return;
        }
        Neptune.commandManager.setPrefix(commands[0]);
        Command.sendMessage("Prefix changed to " + Formatting.GRAY + commands[0]);
    }
}