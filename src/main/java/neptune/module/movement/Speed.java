package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.module.Mod;
import neptune.setting.BooleanSetting;
import neptune.setting.ModeSetting;
import neptune.setting.NumberSetting;
import neptune.utils.player.movement.MoveUtil;

public class Speed extends Mod {
    private final ModeSetting mode = new ModeSetting("Mode", "Strafe", "Strafe");
    //private final NumberSetting speed = new NumberSetting("Speed", 1, 10, 3, 0.1);

    public Speed() {
        super("Speed", "Automatically speeds you up.", Category.MOVEMENT);
        addSetting(mode);
        //speed.setDependency(() -> mode.getMode().equals("Vanilla"));
        //addSetting(speed);
    }

    @EventHandler
    public void onTick() {
        if (mode.isMode("Strafe")) {
            MoveUtil.strafe();
        }
    }
}
