package neptune.features.movement;

import neptune.module.Mod;
import neptune.module.settings.ModeSetting;
import neptune.module.settings.NumberSetting;

public class Flight extends Mod {

    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla");
    private final NumberSetting speed = new NumberSetting("Speed", 1, 35, 5, 5);

    public Flight() {
        super("Flight", "Lets you fly (like a god)", Category.MOVEMENT);
        addSettings(mode, speed);
    }

    @Override
    public void onEnable() {
        if (mode.isMode("Vanilla")) {
            mc.player.getAbilities().setFlySpeed((float) this.speed.getValue());
            mc.player.getAbilities().flying = true;
        }
    }

    @Override
    public void onDisable() {
        if (mode.isMode("Vanilla")) {
            mc.player.getAbilities().flying = false;
        }
    }
}
