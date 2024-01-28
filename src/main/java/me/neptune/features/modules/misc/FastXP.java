package me.neptune.features.modules.misc;

import me.neptune.features.modules.Module;
import net.minecraft.item.Items;

public class FastXP extends Module {
    public FastXP() {
        super("FastXp", "", Category.MISC, true, false, false);
    }

    @Override public void onUpdate() {
        if (nullCheck()) return;

        if (mc.player.isHolding(Items.EXPERIENCE_BOTTLE)) {
            mc.itemUseCooldown = 0;
        }
    }
}
