package neptune.utils.player.combat;

import neptune.utils.MinecraftInterface;

// TODO: Implement Held Item NBT Information Checks
public class HandUtils implements MinecraftInterface {

    public static float getProgress() {
        return mc.player.handSwingProgress;
    }

    public static boolean isSwinging() {
        return mc.player.handSwinging;
    }
}