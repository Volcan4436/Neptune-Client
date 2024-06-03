package neptune.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.BooleanSetting;
import neptune.setting.impl.ModeSetting;
import neptune.utils.player.movement.MoveUtil;

/*
* TODO: Add Modes
*  - Matrix
*  - Legit:
*    - Look Abuse (Abuses Yaw & Pitch)
*    - Ping Abuse (Creates Variation in Ping in sync with Movement to allow increased Velo Values)
*    - Collision Snap (Snaps Yaw and Pitch away from Collision)
*  - Burst (Slowly Build up Velocity when Standing Still then release it in a burst on move)
*  - Float (Float above ground then use velocity to speed hack)
* */
@ModuleInfo(description = "Automatically speeds you up.")
public class Speed extends Module {
    int ticks = 0;
    int groundTicks = 0;
    int airTicks = 0;
    int fallTicks = 0;
    double distanceMoved;
    double fallDistance;
    boolean isGrounded;

    private final ModeSetting mode = new ModeSetting("Mode", "Strafe", "NCP", "Dev");
    private final BooleanSetting jump = new BooleanSetting("Jump").require(() -> mode.is("Strafe"));

    public Speed() {
        addSettings(mode, jump);
    }

    @Listen
    public void onTick(TickEvent event) {
        switch (mode.getMode()) {
            case "Strafe" -> {
                MoveUtil.setSpeed();
                if (jump.isEnabled()) {
                    mc.options.jumpKey.setPressed(false);
                    if (mc.player.isOnGround() && MoveUtil.isMoving()) {
                        mc.player.jump();
                    }
                }
            }
            case "NCP" -> {
                mc.options.jumpKey.setPressed(false);
                if (mc.player.isOnGround()) {
                    mc.player.jump();
                    ticks++;
                }
                if (ticks >= 1 && mc.player.isOnGround()) {
                    MoveUtil.setSpeed();
                }
                if (ticks >= 3) {
                    ticks = 0;
                }
            }
            case "Dev" -> {
                // README: Used for testing purposes
            }
        }
    }
}