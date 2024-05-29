package neptune.module.impl.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.module.api.Mod;
import neptune.module.api.Category;

public class AutoWalk extends Mod {

    public AutoWalk() {
        super("AutoWalk", "Automatically walks for you.", Category.MOVEMENT);
    }

    @EventHandler
    public void onEnable() {
        if (mc.world == null) return; if (mc.player == null) return;
        mc.options.forwardKey.setPressed(true);
    }

    @EventHandler
    public void onDisable() {
        if (mc.world == null) return; if (mc.player == null) return;
        mc.options.forwardKey.setPressed(false);
    }
}
