package neptune.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.ModeSetting;
import neptune.utils.player.movement.DamageBoostUtil;

/*
* TODO: Add Modes
*  - Matrix
*  - Blink
*  - NCP+ (Alternative Bypass)
*  - GroundSnap (Snap to the Ground without modifying velocity)*/
@ModuleInfo(description = "Prevents knockback")
public class Velocity extends Module {
    private final ModeSetting mode = new ModeSetting("Mode", "MultiServer", "NCP", "Dev");

    public Velocity() {
       addSettings(mode);
    }

    double VelZ = 0;

    @Listen
    public void onTick(TickEvent event) {
        switch (mode.getMode()) {
            case "MultiServer" -> {
                double VelX = 0;
                double VelY = mc.player.getVelocity().getY();

                if (!DamageBoostUtil.isBoosting()) {
                    VelX = mc.player.getVelocity().getX();
                    VelY = mc.player.getVelocity().getY();
                    VelZ = mc.player.getVelocity().getZ();
                }

                if (DamageBoostUtil.isHurtTime())
                    mc.player.setVelocity(VelX * -0.8, VelY, VelZ * -0.8);
            }
            case "NCP" -> {
                double motionX = mc.player.getVelocity().getX();
                double motionY = mc.player.getVelocity().getY();
                double motionZ = mc.player.getVelocity().getZ();

                if (DamageBoostUtil.isHurtTime())
                    mc.player.setVelocity(motionX * -0.3, motionY, motionZ * -0.3);
            }
            case "Dev" -> {
                // README: Used for testing purposes
            }
        }
    }
}