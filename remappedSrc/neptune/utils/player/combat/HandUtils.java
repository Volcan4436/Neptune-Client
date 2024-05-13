package neptune.utils.player.combat;

import static neptune.utils.MinecraftInterface.mc;
//todo
// Implement Held Item NBT Information Checks
public class HandUtils {
    public static float getProgress() {
        return mc.player.handSwingProgress;
    }
    public static boolean isSwinging() {
        return mc.player.handSwinging;
    }
}
