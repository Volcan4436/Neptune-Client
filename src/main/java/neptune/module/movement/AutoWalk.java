package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.EventKeyboardInput;
import neptune.module.Mod;

public class AutoWalk extends Mod {

    public AutoWalk() {
        super("AutoWalk", "Automatically walks for you.", Category.MOVEMENT);
    }

    @EventHandler
    public void handleKeyboardInput(EventKeyboardInput event) {
        assert mc.player != null;
        mc.player.input.movementForward = 1.0f;
    }
}
