package neptune.features.movement;

import neptune.event.EventManager;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.module.settings.ModeSetting;
import neptune.module.settings.NumberSetting;

public class Flight extends Mod {

    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla");
    private final NumberSetting speed = new NumberSetting("Speed", 0.1, 5, 5, 0.2);

    public Flight() {
        super("Flight", "Lets you fly (like a god)", Category.MOVEMENT);
        addSettings(mode, speed);
    }

    private final EventManager.EventListener<TickEvent> TickEventListener = e -> {
        if (mode.isMode("Vanilla")) {
            mc.player.getAbilities().setFlySpeed((float) this.speed.getValue());
            mc.player.getAbilities().flying = true;
        }
    };

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
        if (mode.isMode("Vanilla")) {
            mc.player.getAbilities().flying = false;
        }
    }
}
