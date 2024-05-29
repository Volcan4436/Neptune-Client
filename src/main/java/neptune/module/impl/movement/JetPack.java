package neptune.module.impl.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.api.Mod;
import neptune.module.api.Category;

public class JetPack extends Mod {

    public JetPack() {
        super("JetPack", "Allows you to jetpack.", Category.MOVEMENT);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (mc.world == null) return; if (mc.player == null) return;
        if (mc.options.jumpKey.isPressed()) mc.player.jump();
    }
}
