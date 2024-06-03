package neptune.module.impl.combat;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;

/*
 * TODO: Add Modes
 *  - Packet
 *  - Silent Sprint
 * */
@ModuleInfo(description = "Helps Knockback Players")
public class WTap extends Module {
    private int ticks;

    @Listen
    public void onTick(TickEvent event) {
        if (mc.player.handSwinging)
            ticks++;

        switch (ticks) {
            case 1 -> mc.player.setSprinting(true);
            case 2 -> mc.player.setSprinting(false);
            case 3 -> mc.player.setSprinting(true);
        }

        if (!mc.player.handSwinging)
            ticks = 0;
    }
}