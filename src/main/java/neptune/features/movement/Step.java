package neptune.features.movement;

import neptune.module.Mod;
import neptune.module.settings.ModeSetting;
import neptune.module.settings.NumberSetting;

public class Step extends Mod {

    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Strict");
    private final NumberSetting height = new NumberSetting("Height", 1, 5, 1, 0.1);

    public Step() {
        super("Step", "Allows you to go up blocks.", Category.MOVEMENT);
        addSettings(mode, height);
    }

    @Override
    public boolean onEnable() {
        assert mc.player != null;
        mc.player.setStepHeight((float) this.height.getValue());
        return false;
    }

    @Override
    public void onDisable() {
        if (mc.world != null) {
            assert mc.player != null;
            mc.player.setStepHeight(.5f);
        }
    }
}
