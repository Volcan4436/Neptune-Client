package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.setting.BooleanSetting;
import neptune.setting.ModeSetting;
import neptune.setting.NumberSetting;
import neptune.utils.player.movement.MoveUtil;

public class Speed extends Mod {
    int ticks = 0;
    int groundTicks = 0;
    int airTicks = 0;
    int fallTicks = 0;
    double distanceMoved;
    double fallDistance;
    boolean isGrounded;


    //todo
    // add modes:
    // - Matrix
    // - Legit (Look-Abuse (Abuses Yaw and Pitch), Ping Abuse (Creates Variation in Ping in sync with Movement to allow increased Velo Values), Collision Snap (Snaps Yaw and Pitch away from Collision))
    // - Burst (Slowly Build up Velocity when Standing Still then release it in a burst on move)
    // - Float (Float above ground then use velocity to speed hack)
    private final ModeSetting mode = new ModeSetting("Mode", "Strafe", "Strafe", "Strafe-Jump", "NCP", "Dev");
    public Speed() {
        super("Speed", "Automatically speeds you up.", Category.MOVEMENT);
        addSetting(mode);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (mc.world == null) return; if (mc.player == null) return;
        if (mode.isMode("Strafe")) {
            MoveUtil.strafe();
        }
        if (mode.isMode("Strafe-Jump")) {
            mc.options.jumpKey.setPressed(false);
            if(mc.player.isOnGround() && MoveUtil.isMoving()) {
                mc.player.jump();
            }
            MoveUtil.strafe();
        }
        if (mode.isMode("NCP")) {
            mc.options.jumpKey.setPressed(false);
            if (mc.player.isOnGround()) {
                mc.player.jump();
                ticks++;
            }
            if (ticks >= 1 && mc.player.isOnGround()) {
                MoveUtil.strafe();
            }
            if (ticks >= 3) {
                ticks = 0;
            }
        }
        if (mode.isMode("Dev")) {
            //for creating new modes
        }
    }
}
