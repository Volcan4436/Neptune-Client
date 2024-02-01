package neptune.module.movement;

import neptune.module.Mod;
import neptune.setting.BooleanSetting;
import neptune.setting.ModeSetting;

public class Speed extends Mod {
    private final ModeSetting mode = new ModeSetting("Mode", "NCP", "NCP", "StrictStrafe");
    private final BooleanSetting useTimer = new BooleanSetting("Use Timer", false);

    public Speed() {
        super("Speed", "Automatically speeds you up.", Category.MOVEMENT);
        addSetting(mode);
        addSetting(useTimer);
    }
}
