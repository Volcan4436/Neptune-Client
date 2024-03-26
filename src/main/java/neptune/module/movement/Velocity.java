package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.PacketEvent;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.setting.ModeSetting;
import neptune.setting.NumberSetting;
import neptune.utils.player.movement.DamageBoostUtil;

public class Velocity extends Mod {
    //mode
    private final ModeSetting mode = new ModeSetting("Mode", "MultiServer", "MultiServer", "NCP", "Dev");

    public Velocity() {
       super("Velocity", "No Knockback", Category.MOVEMENT);
       addSetting(mode);
    }

    double VelZ = 0;

    @EventHandler
    public void onTick(TickEvent event) {
        if (mc.world == null) return;
        if (mode.isMode("MultiServer")) {
            double VelX = 0;
            double VelY;
            VelY = mc.player.getVelocity().getY();
            if (mc.player == null) {
                return;
            }
            if (!DamageBoostUtil.isBoosting()) {
                VelX = mc.player.getVelocity().getX();
                VelY = mc.player.getVelocity().getY();
                VelZ = mc.player.getVelocity().getZ();
            }
            if (DamageBoostUtil.isHurtTime()) {
                assert mc.player != null;
                mc.player.setVelocity(VelX * -0.8, VelY, VelZ * -0.8);
            }
        }
        if (mode.isMode("NCP")) {
            double motionX = mc.player.getVelocity().getX();
            double motionY = mc.player.getVelocity().getY();
            double motionZ = mc.player.getVelocity().getZ();
            if (DamageBoostUtil.isHurtTime()) {
                assert mc.player != null;
                mc.player.setVelocity(motionX * -0.3, motionY, motionZ * -0.3);
            }
        }
    }
}
