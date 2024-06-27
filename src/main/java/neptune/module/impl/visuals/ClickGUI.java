package neptune.module.impl.visuals;

import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.ui.screens.clickgui.DropDownGUI;
import neptune.ui.screens.modern.dropdown.DropdownGUI;
import org.lwjgl.glfw.GLFW;

@ModuleInfo(description = "Open a configuration", key = GLFW.GLFW_KEY_RIGHT_SHIFT)
public class ClickGUI extends Module {
    private DropDownGUI instance;

    public void onEnable() {
        if (instance == null)
            instance = new DropDownGUI();
        mc.setScreen(new DropDownGUI());
        toggle();
    }
}