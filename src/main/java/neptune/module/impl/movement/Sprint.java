package neptune.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.ModeSetting;

@ModuleInfo(description = "Automatically lets you sprint.")
public class Sprint extends Module {
    private final ModeSetting mode = new ModeSetting("Mode", "Smart", "Stationary", "Omni", "Legit");

    public Sprint() {
        addSettings(mode);
    }

    @Listen
    private void onTick(TickEvent event) {
        if (mc.player.getHungerManager().getFoodLevel() < 6)
            return;

        switch (mode.getMode()) {
            case "Smart" -> {
                if (mc.player.forwardSpeed != 0)
                    mc.player.setSprinting(true);
            }
            case "Stationary" -> mc.player.setSprinting(true);
            case "Omni" -> {
                if (mc.options.forwardKey.isPressed() || mc.options.leftKey.isPressed() || mc.options.rightKey.isPressed() || mc.options.backKey.isPressed())
                    mc.player.setSprinting(true);
            }
            case "Legit" -> {
                if (mc.player.forwardSpeed != 0)
                    mc.options.sprintKey.setPressed(true);
            }
        }
    }
}