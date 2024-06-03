package neptune.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.ModeSetting;

@ModuleInfo(description = "Allows you to fast fall.")
public class FastFall extends Module {
    private final ModeSetting mode = new ModeSetting("Mode", "Matrix", "Dev");

    @Listen
    public void onTick(TickEvent event) {
        switch (mode.getMode()) {
            case "Matrix" -> {
                if (mc.player.fallDistance > 0.8)
                    mc.player.setVelocity(0, -0.54, 0);
            }
            case "Dev" -> {
                // README: Used for testing purposes
            }
        }
    }
}