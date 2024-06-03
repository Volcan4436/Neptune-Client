package neptune.command.impl;

import neptune.command.api.Command;
import neptune.command.api.CommandInfo;

@CommandInfo(aliases = {"cl"}, description = "Clears the chat")
public class Clear extends Command {

    public void onExecute(String message, String[] args) {
        mc.inGameHud.getChatHud().clear(true);
    }
}