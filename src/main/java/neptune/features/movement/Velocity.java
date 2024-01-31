package neptune.features.movement;

import com.google.common.eventbus.Subscribe;
import neptune.event.EventManager;
import neptune.event.events.PacketEvent;
import neptune.event.events.PacketSendEvent;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.module.settings.NumberSetting;

public class Velocity extends Mod {

    private final NumberSetting horizontal = new NumberSetting("Horizontal", -2, 2.5, 0, 0.1);
    private final NumberSetting vertical = new NumberSetting("Vertical", -2, 2.5, 0, 0.1);

    public Velocity() {
       super("Velocity", "No Knockback", Category.MOVEMENT);
    }

    @Subscribe
    void onQ(PacketEvent.Receive per) {
        if (mc.player == null) {
            return;
        }
        //if (per)
    }

    @Override
    public boolean onEnable() {

        return false;
    }

    @Override
    public void onDisable() {

    }
}
