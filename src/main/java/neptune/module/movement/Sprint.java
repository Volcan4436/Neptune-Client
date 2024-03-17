package neptune.module.movement;

import com.google.common.eventbus.Subscribe;
import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.setting.ModeSetting;

public class Sprint extends Mod {

    private final ModeSetting mode = new ModeSetting("Mode", "Smart", "Smart", "Stationary", "Omni");

    public Sprint() {
        super("Sprint", "Automatically lets you sprint.", Category.MOVEMENT);
        addSetting(mode);
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (mc.world == null) return;
        if (mode.isMode("Omni")) {
            if (mc.options.forwardKey.isPressed() || mc.options.leftKey.isPressed() || mc.options.rightKey.isPressed() || mc.options.backKey.isPressed()) {
                mc.player.setSprinting(true);
            }
        }
        else if (mode.isMode("Stationary")) {
            mc.player.setSprinting(true);
        }
        else if (mode.isMode("Smart")) {
            if (mc.player.forwardSpeed != 0) {
                mc.player.setSprinting(true);
            }
        }
    };

    @Subscribe
    public void onTick() {
    }
}
