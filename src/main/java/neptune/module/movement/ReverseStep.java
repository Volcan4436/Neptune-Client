package neptune.module.movement;

import neptune.module.Mod;
import neptune.setting.BooleanSetting;
import neptune.setting.NumberSetting;

import java.util.Objects;

public class ReverseStep extends Mod {

    private final BooleanSetting strict = new BooleanSetting("Strict", false);
    private final BooleanSetting timer = new BooleanSetting("Timer", false);
    private final NumberSetting fallDistance = new NumberSetting("Fall Distance", 0, 5, 3, 0.1);

    public ReverseStep() {
        super("ReverseStep", "cmon bruh", Category.MOVEMENT);
        addSettings(strict, timer, fallDistance);
    }
}

