package neptune.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;

@ModuleInfo(description = "Allows you to jetpack.")
public class JetPack extends Module {

    @Listen
    public void onTick(TickEvent event) {
        if (mc.options.jumpKey.isPressed())
            mc.player.jump();
    }
}