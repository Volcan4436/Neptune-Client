package neptune.command.impl;

import neptune.command.api.Command;
import neptune.command.api.CommandInfo;
import neptune.module.ModuleManager;

@CommandInfo(description = "Panic command")
public class Panic extends Command {

    public void onExecute(String message, String[] args) {
        ModuleManager.getInstance().getModules().forEach(module -> module.setToggled(false));
    }
}