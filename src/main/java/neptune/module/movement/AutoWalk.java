package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.EventKeyboardInput;
import neptune.module.Mod;

public class AutoWalk extends Mod {

    public AutoWalk() {
        super("AutoWalk", "Automatically walks for you.", Category.MOVEMENT);
    }

    @EventHandler
    public void onEnable() {
        mc.options.forwardKey.setPressed(true);
    }

    @EventHandler
    public void onDisable() {
        mc.options.forwardKey.setPressed(false);
    }
}
