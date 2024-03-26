package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.setting.ModeSetting;
import neptune.setting.NumberSetting;
import net.minecraft.util.math.Vec3d;

public class ElytraFly extends Mod {
    private boolean isFrozen = false;
    NumberSetting Multiplier = new NumberSetting("Speed", 1, 10, 1, 1);
    NumberSetting LeftSpeed = new NumberSetting("LeftSpeed", 1, 10, 1, 1);
    NumberSetting RightSpeed = new NumberSetting("RightSpeed", 1, 10, 1, 1);
    NumberSetting DescendSpeed = new NumberSetting("DescendSpeed", 1, 10, 1, 1);
    ModeSetting Lock = new ModeSetting("Locks", "CursorLock", "Y-Lock", "CursorLock");
    public ElytraFly() {
        super("ElytraFly", "Allows you to fly with elytra.", Category.MOVEMENT);
        addSettings(Multiplier);
        addSettings(LeftSpeed);
        addSettings(RightSpeed);
        addSettings(DescendSpeed);
        addSettings(Lock);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (mc.player.isFallFlying()) {
            boolean moveForward = mc.options.forwardKey.isPressed();
            boolean moveBackward = mc.options.backKey.isPressed();
            boolean moveLeft = mc.options.leftKey.isPressed();
            boolean moveRight = mc.options.rightKey.isPressed();

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
                if (Lock.isMode("CursorLock")) {
                    Vec3d velocity = mc.player.getRotationVector().multiply(Multiplier.getValuefloat()); // Adjust the speed by changing the multiplier
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

            if (mc.options.sneakKey.isPressed()) {
                mc.player.setVelocity(mc.player.getVelocity().x, DescendSpeed.getValue() * -1, mc.player.getVelocity().z);
            }

            if (mc.options.jumpKey.isPressed()) {

                if (!isFrozen) {
                    // Freeze the player's Y-axis movement
                    mc.player.setVelocity(Vec3d.ZERO);
                    isFrozen = true;
                }
                else {
                    // Allow Y-axis movement when space bar is released
                    isFrozen = false;
                }
            }
            else {
                mc.player.setNoGravity(false);
            }
        } else {
            mc.player.setNoGravity(false);
        }
    }

    @Override
    public void onDisable() {
        assert mc.player != null;
        mc.player.setNoGravity(false);
    }
}
