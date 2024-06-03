package neptune.module.impl.visuals;

import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.ModeSetting;

// TODO: Implement
@ModuleInfo(description = "Makes dark things lighter!!")
public class FullBright extends Module {
    private final ModeSetting modeSetting = new ModeSetting("Mode", "Gamma", "Night Vision");

    public FullBright() {
        addSettings(modeSetting);
    }
}