package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.setting.BooleanSetting;
import neptune.setting.ModeSetting;
import neptune.setting.NumberSetting;
import neptune.utils.player.movement.MoveUtil;

public class Speed extends Mod {
    private final ModeSetting mode = new ModeSetting("Mode", "Strafe", "Strafe", "NCP", "Dev");
    //private final NumberSetting speed = new NumberSetting("Speed", 1, 10, 3, 0.1);

    int groundTicks = 0;
    int ticks = 0;
    int airTicks = 0;
    public Speed() {
        super("Speed", "Automatically speeds you up.", Category.MOVEMENT);
        addSetting(mode);
        //speed.setDependency(() -> mode.getMode().equals("Vanilla"));
        //addSetting(speed);
    }

    @EventHandler
    public void onTick(TickEvent event) {
        if (mc.world == null) return;
        if (mode.isMode("Strafe")) {
            MoveUtil.strafe();
        }
        if (mode.isMode("NCP")) {
            mc.options.jumpKey.setPressed(false);
            if (mc.player.isOnGround()) {
                mc.player.jump();
                ticks++;
            }
            if (ticks >= 1 && mc.player.isOnGround()) {
                MoveUtil.strafe();
            }
            if (ticks >= 3) {
                ticks = 0;
            }
        }
    }
}
