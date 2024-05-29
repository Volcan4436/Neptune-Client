package neptune.module.impl.combat;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.api.Mod;
import neptune.module.api.Category;
import neptune.setting.ModeSetting;

public class Critical extends Mod {

    //todo
    // add modes:
    // - Packet
    // - Silent Jump
    // - Silent MiniJump
    // - NCP
    // - Matrix
    // - Float
    private final ModeSetting mode = new ModeSetting("Mode", "Jump", "Jump", "MiniJump", "Dev");

    public Critical() {
        super("Critical", "Allows you to crit.", Category.COMBAT);
        addSetting(mode);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (mode.isMode("Jump")) {
            if (mc.player.isOnGround() && mc.player.handSwinging) {
                mc.options.jumpKey.setPressed(false);
                mc.player.jump();
            }
        }
        else if (mode.isMode("MiniJump")) {
            if (mc.player.isOnGround() && mc.player.handSwinging) {
                mc.options.jumpKey.setPressed(false);
                mc.player.setVelocity(mc.player.getVelocity().x, 0.2, mc.player.getVelocity().z);
            }
        }
        else if (mode.isMode("Dev")) {
            // used for testing
        }
    }
}
