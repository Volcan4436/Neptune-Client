package neptune.module.impl.misc;

import io.github.nevalackin.radbus.Listen;
import neptune.command.CommandManager;
import neptune.command.api.Command;
import neptune.event.impl.game.ChatEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.utils.ChatUtils;

import java.util.Arrays;

@ModuleInfo(description = "Allow you to control various features of the client via a commands")
public class Commands extends Module {

    @Listen
    public void onChat(ChatEvent event) {
        char prefix = CommandManager.getInstance().getPrefix();
        String message = event.getMessage();

        // Check for prefix
        if (message.charAt(0) != prefix)
            return;

        event.cancel();

        // Remove prefix
        message = message.substring(1);

        if (message.isEmpty())
            message = "help";

        String[] split = message.split("\\s");
        String name = split[0];

        // Remove the command from the array
        if (split.length > 1)
            split = Arrays.copyOfRange(split, 1, split.length);

        // Remove the command from message
        message = message.replaceFirst("\\S+", "").trim();

        for (Command command : CommandManager.getInstance().getCommands()) {
            if (command.getName().equalsIgnoreCase(name) || Arrays.asList(command.getAliases()).contains(name.toLowerCase())) {
                command.onExecute(message, split);
                return;
            }
        }

        ChatUtils.messagef("Unknown command, Use '%shelp' to get started.", prefix);
    }
}