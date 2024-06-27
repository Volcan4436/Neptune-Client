package neptune.command.impl;

import neptune.command.api.Command;
import neptune.command.api.CommandInfo;
import neptune.module.ModuleManager;
import neptune.utils.ChatUtils;
import neptune.utils.KeyUtils;
import net.minecraft.util.Formatting;

@CommandInfo(description = "Binds a specified module", aliases = {"b"})
public class Bind extends Command {

    public void onExecute(String message, String[] args) {
        if (args.length <= 1) {
            if (args[0].equalsIgnoreCase("clear")) {
                ModuleManager.getInstance().getModules().forEach(module -> module.setKey(0));
                ChatUtils.messageBranding("All modules have been unbound.");
                return;
            }

            error("Usage", "bind <module> <key>");
            return;
        }

        String moduleName = args[0];
        String key = args[1];

        if (!KeyUtils.isKeyValid(key)) {
            error("Key", "valid key");
            return;
        }

        ModuleManager.getInstance().getModules().stream()
                .filter(module -> module.getName().equalsIgnoreCase(moduleName))
                .findFirst()
                .ifPresentOrElse(
                        module -> {
                            module.setKey(KeyUtils.getKey(key));
                            ChatUtils.messagefBranding("Bound %s to %s", Formatting.WHITE + module.getName() + Formatting.GRAY, Formatting.WHITE + key);
                        }, () -> error("Module", "valid module")
                );
    }
}