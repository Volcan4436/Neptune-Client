package me.neptune.modules.module.player;

import me.neptune.modules.module.Module;
import net.minecraft.item.Items;

public class FastPlace extends Module {
    public FastPlace() {
        super("FastPlace", "", Category.PLAYER, true, false, false);
    }

    @Override public void onUpdate() {
        if (nullCheck()) return;

        if (mc.player.isHolding(Items.EXPERIENCE_BOTTLE)) {
            mc.itemUseCooldown = 0;
        }
    }
}
