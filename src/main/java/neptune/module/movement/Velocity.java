package neptune.module.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.PacketEvent;
import neptune.module.Mod;
import neptune.setting.NumberSetting;

public class Velocity extends Mod {

    private final NumberSetting horizontal = new NumberSetting("Horizontal", -2, 2.5, 0, 0.1);
    private final NumberSetting vertical = new NumberSetting("Vertical", -2, 2.5, 0, 0.1);

    public Velocity() {
       super("Velocity", "No Knockback", Category.MOVEMENT);
       addSettings(horizontal, vertical);
    }

    @EventHandler
    private void onQ(PacketEvent.Receive per) {
        if (mc.player == null) {
            return;
        }
        //if (per)
    }




}
