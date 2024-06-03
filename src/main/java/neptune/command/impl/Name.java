package neptune.command.impl;

import neptune.command.api.Command;
import neptune.command.api.CommandInfo;
import neptune.utils.ChatUtils;


@CommandInfo(aliases = {"nm"}, description = "Copies your username to clipboard")
public class Name extends Command {

    public void onExecute(String message, String[] args) {
        mc.keyboard.setClipboard(mc.player.getNameForScoreboard());
        ChatUtils.messageBranding("Username copied to clipboard!");
    }
}