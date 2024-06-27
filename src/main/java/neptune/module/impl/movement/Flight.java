package neptune.module.impl.movement;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.ModeSetting;
import neptune.utils.player.movement.MoveUtil;

@ModuleInfo(description = "Allows you to fly.")
public class Flight extends Module {
    ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Velocity", "JetPack");

    public Flight() {
        addSettings(mode);
    }

    public void onDisable() {
        if (mode.is("Vanilla"))
            mc.player.getAbilities().flying = false;
    }

    @Listen
    public void onTick(TickEvent event) {
        switch (mode.getMode()) {
            case "Vanilla" -> {
                mc.player.getAbilities().allowFlying = true;
                mc.player.getAbilities().flying = true;
            }
            case "Velocity" -> {
                if (mc.options.jumpKey.isPressed())
                    mc.player.setVelocity(0, 0.5, 0);
                else if (mc.options.sneakKey.isPressed())
                    mc.player.setVelocity(0, -0.5, 0);
                else
                    mc.player.setVelocity(0, 0, 0);

                MoveUtil.setSpeed(1.5);
            }
            case "JetPack" -> {
                if (mc.options.jumpKey.isPressed())
                    mc.player.jump();
            }
        }
    }
}