package neptune.module.impl.movement;

import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;

@ModuleInfo(description = "Automatically walks for you.")
public class AutoWalk extends Module {

    public void onEnable() {
        mc.options.forwardKey.setPressed(true);
    }

    public void onDisable() {
        mc.options.forwardKey.setPressed(false);
    }
}