package neptune.utils.player.movement;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import static neptune.utils.MinecraftInterface.mc;

public class MoveUtil {


    private static double speed = 1.0; // Replace with your desired speed value

    public static void strafe() {
        strafe(speed());
    }

    public static double speed() {
        return Math.sqrt(Math.pow(mc.player.getVelocity().getX(), 2) + Math.pow(mc.player.getVelocity().getZ(), 2));
    }

    public static boolean isMoving() {
        return  mc.player.forwardSpeed != 0 || mc.player.sidewaysSpeed != 0;
    }

    public static void strafe(double speed) {
        if (!isMoving()) {
            return;
        }

        double yaw = getDirection();
        double motionX = -MathHelper.sin((float) yaw) * speed; // Inverted direction
        double motionZ = MathHelper.cos((float) yaw) * speed; // Inverted direction
        mc.player.setVelocity(new Vec3d(motionX, mc.player.getVelocity().getY(), motionZ));
    }

    public static double getDirection() {
        double rotationYaw = MinecraftClient.getInstance().player.getYaw();
        if (MinecraftClient.getInstance().player.input.movementForward < 0) {
            rotationYaw += 180;
        }
        double forward = 1;
        if (MinecraftClient.getInstance().player.input.movementForward < 0) {
            forward = -0.5;
        } else if (MinecraftClient.getInstance().player.input.movementForward > 0) {
            forward = 0.5;
        }
        if (MinecraftClient.getInstance().player.input.movementSideways > 0) {
            rotationYaw -= 90 * forward;
        }
        if (MinecraftClient.getInstance().player.input.movementSideways < 0) {
            rotationYaw += 90 * forward;
        }
        return Math.toRadians(rotationYaw);
    }
}
