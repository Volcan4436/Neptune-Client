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
    private final ModeSetting mode = new ModeSetting("Mode", "MultiServer", "MultiServer", "Dev");

    public Velocity() {
       super("Velocity", "No Knockback", Category.MOVEMENT);
       addSetting(mode);
    }

    double VelZ = 0;

    @EventHandler
    private final void onTickEvent(TickEvent event) {

        if (mode.getMode() == "MultiServer") {
            double VelX = 0;
            double VelY;
            VelY = mc.player.getVelocity().getY();

            if (mc.player == null) {
                return;
            }

            if (!DamageBoostUtil.isBoosting()) {
                assert mc.player != null;
                VelX = mc.player.getVelocity().getX();
                VelY = mc.player.getVelocity().getY();
                VelZ = mc.player.getVelocity().getZ();
            }
            if (DamageBoostUtil.isHurtTime()) {
                assert mc.player != null;
                mc.player.setVelocity(VelX * -0.8, VelY, VelZ * -0.8);
            }
        }

    }
}
