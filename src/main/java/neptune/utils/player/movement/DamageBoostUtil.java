package neptune.utils.player.movement;

import static neptune.utils.MinecraftInterface.mc;

public class DamageBoostUtil {

    private static double BoostType1Speed = 0.7;

    //Used to set the Boosting Status
    public static boolean isBoosting() {
        return false;
    }

    //Only Runs if you are outside hurttime
    public static boolean isHurtTime() {
        return mc.player.hurtTime > 0;
    }

    //AirStrafeBoost
    public static void BoostType1() {
        if (isBoosting() == true && !mc.player.isOnGround()) {
            //untested
            mc.player.setVelocity(BoostType1Speed * mc.player.getVelocity().getX(), mc.player.getVelocity().getY(), BoostType1Speed * mc.player.getVelocity().getZ());
        }
    }
}