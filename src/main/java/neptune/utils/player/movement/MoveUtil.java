package neptune.utils.player.movement;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import static neptune.utils.MinecraftInterface.mc;

public class MoveUtil {

    public static double getSpeed() {
        return Math.hypot(mc.player.getVelocity().getX(), mc.player.getVelocity().getZ());
    }

    public static double getDirection() {
        double rotationYaw = mc.player.getYaw();
        double forward = 1;

        if (mc.player.input.movementForward < 0)
            rotationYaw += 180;

        if (mc.player.input.movementForward < 0)
            forward = -0.5;
        else if (mc.player.input.movementForward > 0)
            forward = 0.5;

        if (mc.player.input.movementSideways > 0)
            rotationYaw -= 90 * forward;

        if (mc.player.input.movementSideways < 0)
            rotationYaw += 90 * forward;

        return Math.toRadians(rotationYaw);
    }

    public static void setSpeed(double speed) {
        if (!isMoving())
            return;

        float yaw = (float) getDirection();
        double motionX = -MathHelper.sin(yaw) * speed; // Inverted direction
        double motionZ = MathHelper.cos(yaw) * speed; // Inverted direction
        mc.player.setVelocity(new Vec3d(motionX, mc.player.getVelocity().getY(), motionZ));
    }

    public static void setSpeed() {
        setSpeed(getSpeed());
    }

    public static boolean isMoving() {
        return mc.player.forwardSpeed != 0 || mc.player.sidewaysSpeed != 0;
    }
}