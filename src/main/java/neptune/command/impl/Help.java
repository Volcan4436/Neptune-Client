package neptune.command.impl;

import neptune.Neptune;
import neptune.command.Command;
import neptune.utils.ChatUtils;
import neptune.command.CommandManager;

public class Help extends Command {

    public Help() {
        super("help", "Shows all commands", "");
    }

    @Override
    public void onCmd(String message, String[] args) {
        // Initialize HelpMsg with the header
        StringBuilder HelpMsg = new StringBuilder("Available commands: \n");

        // Iterate over the cmds list from the CommandManager singleton instance
        for (Command cmd : Neptune.getInstance().getCommandManager().getCmds()) {
            // Append each command's name to HelpMsg
            HelpMsg.append(cmd.getName()).append("\n");
        }

        // Add the HelpMsg to the chat
        ChatUtils.addChatMessage(HelpMsg.toString());
    }
}
