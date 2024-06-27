package neptune.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.BooleanSetting;
import neptune.setting.impl.ModeSetting;
import neptune.setting.impl.NumberSetting;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.util.math.Vec3d;

@ModuleInfo(description = "Allows you to fly with elytra.")
public class ElytraFly extends Module {
    private boolean isFrozen = false;
    boolean launch = false;
    boolean boosted = false;
    int timer = 0;
    int ticks = 0;

    ModeSetting Mode = new ModeSetting("Mode", "Vanilla", "Matrix", "Dev");

    //Vanilla
    NumberSetting Multiplier = new NumberSetting("Speed", 1, 1, 10, 1);
    NumberSetting LeftSpeed = new NumberSetting("LeftSpeed", 1, 1, 10, 1);
    NumberSetting RightSpeed = new NumberSetting("RightSpeed", 1, 1, 10, 1);
    NumberSetting DescendSpeed = new NumberSetting("DescendSpeed", 1, 1, 10, 1);
    NumberSetting fastFall = new NumberSetting("FastFall", 0, 0, 5, 0.1);
    ModeSetting Lock = new ModeSetting("Locks", "Y-Lock", "CursorLock");

    //Matrix
    NumberSetting BoostAmount = new NumberSetting("Boost-Amount", 2, 0.1, 5, 0.1);
    NumberSetting FallDistance = new NumberSetting("Fall-Distance", 0.5, 0.1, 1.5, 0.1);
    ModeSetting MBoostMode = new ModeSetting("Boost-Mode", "Yaw", "Pitch", "Dev");
    BooleanSetting AirBoost = new BooleanSetting("Air-Boost");
    BooleanSetting SlowDown = new BooleanSetting("Slow-Down");
    NumberSetting AirBoostAmount = new NumberSetting("Air-Boost-Amount", 0.5, 0.1, 5, 0.1);

    public ElytraFly() {
        addSettings(Multiplier, LeftSpeed, RightSpeed, DescendSpeed, fastFall, Lock, Mode, BoostAmount, FallDistance, MBoostMode, AirBoost, SlowDown, AirBoostAmount);
    }

    @Listen
    public void onTick(TickEvent event) {
        switch (Mode.getMode()) {
            case "Vanilla" -> {
                if (mc.player.isFallFlying()) {
                    boolean moveForward = mc.options.forwardKey.isPressed();
                    boolean moveBackward = mc.options.backKey.isPressed();
                    boolean moveLeft = mc.options.leftKey.isPressed();
                    boolean moveRight = mc.options.rightKey.isPressed();

                    // Fast Fall
                    if (fastFall.getValueFloat() > 0)
                        if (mc.player.hurtTime == 0)
                            mc.player.setVelocity(mc.player.getVelocity().x, fastFall.getValueFloat(), mc.player.getVelocity().z);

                    if (moveForward && (moveLeft || moveRight)) {
                        float yaw = mc.player.getYaw(); // Get the player's yaw rotation
                        double radian;
                        if (moveLeft) {
                            radian = Math.toRadians(yaw - 45.0); // Calculate the diagonal angle for forward and left
                        } else {
                            radian = Math.toRadians(yaw + 45.0); // Calculate the diagonal angle for forward and right
                        }

                        double speed = Multiplier.getValue(); // Adjust the speed as desired
                        double velX = -Math.sin(radian) * speed; // Calculate the x component of velocity
                        double velZ = Math.cos(radian) * speed; // Calculate the z component of velocity

                        mc.player.setVelocity(velX, mc.player.getVelocity().y, velZ);
                    } else if (moveForward) {
                        if (Lock.is("CursorLock")) {
                            Vec3d velocity = mc.player.getRotationVector().multiply(Multiplier.getValueFloat()); // Adjust the speed by changing the multiplier
                            mc.player.setVelocity(velocity.x, velocity.y, velocity.z);
                        } else {
                            Vec3d forward = mc.player.getRotationVector(); // Get the player's forward direction vector
                            double speed = Multiplier.getValue(); // Adjust the speed as desired
                            double velX = forward.x * speed; // Calculate the x component of velocity
                            double velZ = forward.z * speed; // Calculate the z component of velocity
                            mc.player.setVelocity(velX, 0.0, velZ);
                        }
                    } else if (moveBackward && (moveLeft || moveRight)) {
                        float yaw = mc.player.getYaw(); // Get the player's yaw rotation
                        double radian;
                        if (moveLeft) {
                            radian = Math.toRadians(yaw + 45.0); // Calculate the diagonal angle for backward and left
                        } else {
                            radian = Math.toRadians(yaw - 45.0); // Calculate the diagonal angle for backward and right
                        }

                        double speed = Multiplier.getValue(); // Adjust the speed as desired
                        double velX = Math.sin(radian) * speed; // Calculate the x component of velocity
                        double velZ = -Math.cos(radian) * speed; // Calculate the z component of velocity

                        mc.player.setVelocity(velX, mc.player.getVelocity().y, velZ);
                    }

                    if (mc.options.sneakKey.isPressed())
                        mc.player.setVelocity(mc.player.getVelocity().x, DescendSpeed.getValue() * -1, mc.player.getVelocity().z);

                    if (mc.options.jumpKey.isPressed()) {

                        if (!isFrozen) {
                            // Freeze the player's Y-axis movement
                            mc.player.setVelocity(Vec3d.ZERO);
                            isFrozen = true;
                        } else {
                            // Allow Y-axis movement when space bar is released
                            isFrozen = false;
                        }
                    } else {
                        mc.player.setNoGravity(false);
                    }
                } else {
                    mc.player.setNoGravity(false);
                }
            }
            case "Matrix" -> {
                if (!mc.player.isFallFlying() && mc.player.isOnGround()) {
                    mc.player.jump();
                }
                if (mc.player.fallDistance > FallDistance.getValueFloat() && !mc.player.isFallFlying() && !launch) {
                    mc.player.networkHandler.sendPacket(new ClientCommandC2SPacket(mc.player, ClientCommandC2SPacket.Mode.START_FALL_FLYING));
                    MatrixElytra(BoostAmount.getValueFloat());
                    launch = true;
                }
                if (mc.player.isFallFlying()) {
                    launch = false;
                }
                if (AirBoost.isEnabled() && mc.player.isFallFlying()) {
                    if (mc.options.jumpKey.isPressed() && !boosted) {
                        MatrixElytra(AirBoostAmount.getValueFloat());
                        boosted = true;
                    }
                    else if (!mc.options.jumpKey.isPressed()) {
                        boosted = false;
                    }

                }
                if (SlowDown.isEnabled()) {
                    if (mc.options.sneakKey.isPressed()) {
                        MatrixElytra(0.1);
                    }
                }
            }
            case "Dev" -> {
                // README: Used for testing purposes
            }
        }
    }

    public static void MatrixElytra(double BoostValue) {
        // Get the player's rotation pitch and yaw
        float rotationYaw = mc.player.getYaw();
        float rotationPitch = mc.player.getPitch();

        // Convert degrees to radians
        double yawRadians = Math.toRadians(rotationYaw);
        double pitchRadians = Math.toRadians(rotationPitch);

        // Calculate the direction vector
        double x = -Math.sin(yawRadians) * Math.cos(pitchRadians);
        double y = -Math.sin(pitchRadians);
        double z = Math.cos(yawRadians) * Math.cos(pitchRadians);

        // Normalize the direction vector
        double length = Math.sqrt(x * x + y * y + z * z);
        x /= length;
        y /= length;
        z /= length;

        // Scale the direction vector by the speed
        x *= BoostValue;
        y *= BoostValue;
        z *= BoostValue;

        // Set the velocity in the direction the player is facing
        mc.player.setVelocity(x, y, z);
    }

    public void onDisable() {
        mc.player.setNoGravity(false);
    }
}