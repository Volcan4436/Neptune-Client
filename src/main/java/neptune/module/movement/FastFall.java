package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.setting.ModeSetting;

public class FastFall extends Mod {


    private final ModeSetting mode = new ModeSetting("Mode", "Matrix", "Matrix", "Dev");

    public FastFall() {
        super("FastFall", "Allows you to fast fall.", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (mc.world == null) return; if (mc.player == null) return;
        if (mode.isMode("Matrix")) {
            if (mc.player.fallDistance > 0.8) {
                mc.player.setVelocity(0, -0.54, 0);
            }
        }
        if (mode.isMode("Dev")) {
            return;
        }
        else return;
    }
}
