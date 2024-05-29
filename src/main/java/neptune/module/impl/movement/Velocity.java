package neptune.module.impl.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.api.Mod;
import neptune.module.api.Category;
import neptune.setting.ModeSetting;
import neptune.utils.player.movement.DamageBoostUtil;

public class Velocity extends Mod {
    //mode
    //todo
    // add modes:
    // - Matrix
    // - Blink
    // - NCP+ (Alternative Bypass)
    // - GroundSnap (Snap to the Ground without modifying velocity)
    private final ModeSetting mode = new ModeSetting("Mode", "MultiServer", "MultiServer", "NCP", "Dev");

    public Velocity() {
       super("Velocity", "No Knockback", Category.MOVEMENT);
       addSetting(mode);
    }

    double VelZ = 0;

    @EventHandler
    public void onTick(TickEvent event) {
        if (mc.world == null) return; if (mc.player == null) return;
        if (mode.isMode("MultiServer")) {
            double VelX = 0;
            double VelY;
            VelY = mc.player.getVelocity().getY();
            if (!DamageBoostUtil.isBoosting()) {
                VelX = mc.player.getVelocity().getX();
                VelY = mc.player.getVelocity().getY();
                VelZ = mc.player.getVelocity().getZ();
            }
            if (DamageBoostUtil.isHurtTime()) {
                mc.player.setVelocity(VelX * -0.8, VelY, VelZ * -0.8);
            }
        }
        if (mode.isMode("NCP")) {
            double motionX = mc.player.getVelocity().getX();
            double motionY = mc.player.getVelocity().getY();
            double motionZ = mc.player.getVelocity().getZ();
            if (DamageBoostUtil.isHurtTime()) {
                mc.player.setVelocity(motionX * -0.3, motionY, motionZ * -0.3);
            }
        }
    }
}
