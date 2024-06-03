package neptune.command.impl;

import neptune.command.CommandManager;
import neptune.command.api.Command;
import neptune.command.api.CommandInfo;
import neptune.utils.ChatUtils;

@CommandInfo(aliases = {"cl"}, description = "Clears the chat")
public class Prefix extends Command {

    public void onExecute(String message, String[] args) {
        if (args.length != 1) {
            ChatUtils.messageBranding("Invalid syntax! Please use: .bind <prefix>");
            return;
        }
        CommandManager.getInstance()
                .setPrefix(args[0].charAt(0));
    }
}