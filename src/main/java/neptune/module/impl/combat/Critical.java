package neptune.module.impl.combat;

import io.github.nevalackin.radbus.Listen;
import neptune.event.impl.game.TickEvent;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.ModeSetting;

/*
 * TODO: Add Modes
 *  - Packet
 *  - Silent Jump
 *  - Silent MiniJump
 *  - NCP
 *  - Matrix
 *  - Float
 * */
@ModuleInfo(description = "Allows you to crit more.")
public class Critical extends Module {
    private final ModeSetting mode = new ModeSetting("Mode", "Jump", "MiniJump", "Dev");

    public Critical() {
        addSettings(mode);
    }

    @Listen
    public void onTick(TickEvent event) {
        switch (mode.getMode()) {
            case "Jump" -> {
                if (mc.player.isOnGround() && mc.player.handSwinging) {
                    mc.options.jumpKey.setPressed(false);
                    mc.player.jump();
                }
            }
            case "MiniJump" -> {
                if (mc.player.isOnGround() && mc.player.handSwinging) {
                    mc.options.jumpKey.setPressed(false);
                    mc.player.setVelocity(mc.player.getVelocity().x, 0.2, mc.player.getVelocity().z);
                }
            }
            case "Dev" -> {
                // README: Used for testing purposes
            }
        }
    }
}