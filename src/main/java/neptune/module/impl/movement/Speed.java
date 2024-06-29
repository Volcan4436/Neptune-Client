package neptune.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.BooleanSetting;
import neptune.setting.impl.ModeSetting;
import neptune.setting.impl.NumberSetting;
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

    private final ModeSetting mode = new ModeSetting("Mode", "Legit", "Strafe", "Hypixel", "Spartan-Position", "Boost", "NCP", "Dev");
    private final BooleanSetting jump = new BooleanSetting("Jump").require(() -> mode.is("Strafe"));
    //Boost Speed
    private final NumberSetting boostSpeed = new NumberSetting("Boost Speed", 0.6, 0.01, 2, 0.01).require(() -> mode.is("Boost"));

    public Speed() {
        addSettings(mode, jump);
    }

    @Listen
    public void onTick(TickEvent event) {
        switch (mode.getMode()) {

            //Todo: Add Legit Strafe (Rotation Snap Silent) || Add Lag Buffer (Increase Speed Slightly when Lagging to improve Movement)
            case "Legit" -> {
                mc.options.jumpKey.setPressed(false);
                if (MoveUtil.isMoving() && mc.player.isOnGround()) {
                    mc.player.jump();
                }
            }

            //Todo: Add Speed Option
            case "Strafe" -> {
                MoveUtil.setSpeed(); //Start to Strafe
                if (jump.isEnabled()) {
                    mc.options.jumpKey.setPressed(false);
                    if (mc.player.isOnGround() && MoveUtil.isMoving())
                        mc.player.jump();
                }
            }

            //Todo: Add Combat Strafe || Stop in Water || Add Omni Sprint Bypass
            case "Hypixel" -> {
                mc.options.jumpKey.setPressed(false);
                if (mc.player.isUsingItem()) return;
                if (mc.player.isSneaking()) return;
                if (mc.player.isCrawling()) return;
                if (mc.player.isSwimming()) return;
                if (MoveUtil.isMoving()) {
                    if (mc.player.isOnGround()) {
                        mc.player.jump();
                        MoveUtil.setSpeed(0.485); //Does the Ground Strafe
                    }
                }
            }

            //Todo: Stop Phasing Issue
            case "Spartan-Position" -> {
                float yaw = mc.player.getYaw();
                double radians = Math.toRadians(yaw);

                // Calculate the offset based on the player's viewing direction
                double xOffset = -Math.sin(radians) * 0.3; // Calculate X offset based on yaw
                double zOffset = Math.cos(radians) * 0.3;  // Calculate Z offset based on yaw
                mc.options.jumpKey.setPressed(false);
                if (mc.player.horizontalCollision) return;
                if (mc.player.isOnGround()) {
                    if (mc.options.forwardKey.isPressed()) {
                        mc.player.updatePosition(mc.player.getX() + xOffset, mc.player.getY(), mc.player.getZ() + zOffset);
                    } else if (mc.options.backKey.isPressed()) {
                        mc.player.updatePosition(mc.player.getX() - xOffset, mc.player.getY(), mc.player.getZ() - zOffset);
                    } else if (mc.options.leftKey.isPressed()) {
                        mc.player.updatePosition(mc.player.getX() + zOffset, mc.player.getY(), mc.player.getZ() - xOffset);
                    } else if (mc.options.rightKey.isPressed()) {
                        mc.player.updatePosition(mc.player.getX() - zOffset, mc.player.getY(), mc.player.getZ() + xOffset);
                    }
                }
            }

            case "Boost" -> {
                mc.options.jumpKey.setPressed(false);
                if (mc.player.hurtTime == 1) {
                    mc.player.setVelocity(mc.player.getVelocity().getX() * boostSpeed.getValue(), mc.player.getVelocity().getY(), mc.player.getVelocity().getZ() * boostSpeed.getValue());
                }
            }

            //Todo: Make this Fast
            case "NCP" -> {
                mc.options.jumpKey.setPressed(false);
                if (mc.player.isOnGround()) {
                    mc.player.jump();
                    ticks++;
                }

                if (ticks >= 1 && mc.player.isOnGround())
                    MoveUtil.setSpeed();

                if (ticks >= 3)
                    ticks = 0;
            }

            case "Dev" -> {
                // README: Used for testing purposes
            }
        }
    }
}