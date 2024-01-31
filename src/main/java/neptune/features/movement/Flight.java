package neptune.features.movement;

import neptune.event.EventManager;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.module.settings.ModeSetting;
import neptune.module.settings.NumberSetting;

public class Flight extends Mod {

    double startHeight;
    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Vulcan");
    private final NumberSetting speed = new NumberSetting("Speed", 0.1, 5, 5, 0.1);
    private final NumberSetting vulcanclip = new NumberSetting("Clip", 1, 100, 10, 0.1);
    private final NumberSetting shouldClip = new NumberSetting("Should Clip", 1, 100, 10, 0.1);

    public Flight() {
        super("Flight", "Lets you fly (like a god)", Category.MOVEMENT);
        addSettings(mode, speed);
    }

    private final EventManager.EventListener<TickEvent> TickEventListener = e -> {
        if (mode.isMode("Vanilla")) {
            mc.player.getAbilities().setFlySpeed((float) this.speed.getValue());
            mc.player.getAbilities().flying = true;
        }
        if (mode.isMode("Vulcan")) {

        }
    };

    @Override
    public boolean onEnable() {

        //startHeight = mc.player.getY();
        //if (shouldClip.getValue()) {
            //mc.player.updatePosition(mc.player.getX(), mc.player.getY() + vulcanclip.getValue(), mc.player.getZ());
        //}
        return false;
    }

    @Override
    public void onDisable() {
        if (mode.isMode("Vanilla")) {
            mc.player.getAbilities().flying = false;
        }
    }
}
