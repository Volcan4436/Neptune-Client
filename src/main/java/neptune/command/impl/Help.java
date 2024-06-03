package neptune.command.impl;

import neptune.command.CommandManager;
import neptune.command.api.Command;
import neptune.command.api.CommandInfo;
import neptune.utils.ChatUtils;

@CommandInfo(aliases = {"h"}, description = "Displays a list of commands")
public class Help extends Command {

    public void onExecute(String message, String[] args) {
        ChatUtils.message("Commands:");
        CommandManager.getInstance().getCommands().forEach(command -> {
            String commandInfo = "  " + command.getName() + " : " + command.getDescription();
            ChatUtils.message(commandInfo);
        });
    }
}