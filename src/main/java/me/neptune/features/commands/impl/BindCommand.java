package me.neptune.features.commands.impl;

import com.google.common.eventbus.Subscribe;
import me.neptune.Neptune;
import me.neptune.event.impl.KeyEvent;
import me.neptune.features.commands.Command;
import me.neptune.features.settings.Bind;
import me.neptune.util.KeyboardUtil;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.security.Key;

public class BindCommand
        extends Command {
    private boolean listening;
    private Module module;

    public BindCommand() {
        super("bind", new String[]{"<module>"});
        EVENT_BUS.register(this);
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 1) {
            sendMessage("Please specify a module.");
            return;
        }
        String moduleName = commands[0];
        Module module = Neptune.moduleManager.getModuleByName(moduleName);
        if (module == null) {
            sendMessage("Unknown module '" + module + "'!");
            return;
        }

        sendMessage(Formatting.GRAY + "Press a key.");
        listening = true;
        this.module = module;
    }

    @Subscribe private void onKey(KeyEvent event) {
        if (nullCheck() || !listening) return;
        listening = false;
        if (event.getKey() == GLFW.GLFW_KEY_ESCAPE) {
            sendMessage(Formatting.GRAY + "Operation cancelled.");
            return;
        }

        sendMessage("Bind for " + Formatting.GREEN + module.getName() + Formatting.WHITE + " set to " + Formatting.GRAY + KeyboardUtil.getKeyName(event.getKey()));
        module.bind.setValue(new Bind(event.getKey()));
    }

}