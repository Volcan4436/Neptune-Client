package me.neptune.api.command.impl;

import me.neptune.api.command.Command;
import me.neptune.api.module.Module;
import me.neptune.managers.ManagerHelper;
import me.neptune.util.minecraft.ChatUtil;
import net.minecraft.util.Formatting;

public class ToggleCommand extends Command
{
    public ToggleCommand() {
        super("toggle", new String[] { "module" });
    }

    public void run(String[] args) {

        if(args.length >= 1) {
            for (Module mod : ManagerHelper.MODULES.getRegistered()) {
                if (mod.getName().equalsIgnoreCase(args[1]))
                {
                    mod.toggle();
                    ChatUtil.sendMessage((!mod.isEnabled() ? Formatting.RED : Formatting.GREEN) + "Toggling " + Formatting.WHITE + mod.getName() + ".", true);
                }
            }
        } else {
            ChatUtil.sendMessage("Not enough arguments!", false);
        }
    }
}
