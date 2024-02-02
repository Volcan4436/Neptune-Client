package neptune.module.movement;

import neptune.module.Mod;
import neptune.setting.BooleanSetting;
import neptune.setting.ModeSetting;
import neptune.setting.NumberSetting;

public class Speed extends Mod {
    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla");
    private final NumberSetting vanillaSpeed = new NumberSetting("Speed", 1, 10, 3, 0.1);

    public Speed() {
        super("Speed", "Automatically speeds you up.", Category.MOVEMENT);
        addSetting(mode);
        vanillaSpeed.setDependency(() -> mode.getMode().equals("Vanilla"));
        addSetting(vanillaSpeed);
    }

    public void onEnable() {
        if (mode.isMode("Vanilla")) {
            mc.player.getAbilities().setWalkSpeed((float) this.vanillaSpeed.getValue());
        }
    }

    public void onDisable() {
        if (mode.isMode("Vanilla")){
            mc.player.getAbilities().setWalkSpeed(1.0F);
        }
    }
}
