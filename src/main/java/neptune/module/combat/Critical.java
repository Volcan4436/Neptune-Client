package neptune.module.combat;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.setting.ModeSetting;

public class Critical extends Mod {

    private final ModeSetting mode = new ModeSetting("Mode", "Jump", "Jump", "MiniJump", "Dev");

    public Critical() {
        super("Critical", "Allows you to crit.", Category.COMBAT);
        addSetting(mode);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (mode.isMode("Jump")) {
            if (mc.player.isOnGround() && mc.player.handSwinging) {
                mc.player.jump();
            }
        }
        else if (mode.isMode("MiniJump")) {
            if (mc.player.isOnGround() && mc.player.handSwinging) {
                mc.player.setVelocity(mc.player.getVelocity().x, 0.2, mc.player.getVelocity().z);
            }
        }
        else if (mode.isMode("Dev")) {
            return;
        }
    }
}
