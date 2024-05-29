package neptune.module.impl.combat;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.api.Mod;
import neptune.module.api.Category;

public class Wtap extends Mod {

    public static int ticks;

    public Wtap() {
        super("WTap", "Helps Knockback Players", Category.MOVEMENT);
    }

    //todo
    // add Packet Mode
    // add Silent Sprint
    @EventHandler
    public void onTick(TickEvent event) {
        if (mc.world == null) return; if (mc.player == null) return;
        if (mc.player.handSwinging) {
            ticks++;
        }
        if (ticks == 1) {
            mc.player.setSprinting(true);
        }
        if (ticks == 2) {
            mc.player.setSprinting(false);
        }
        if (ticks == 3) {
            mc.player.setSprinting(true);
        }
        if (!mc.player.handSwinging) {
            ticks = 0;
        }
    }
}
