package neptune.module.player;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.setting.ModeSetting;

public class NoFall extends Mod {

    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "NCP", "Volcanware", "Dev");
    public NoFall() {
        super("NoFall", "No Fall", Category.MOVEMENT);
        addSetting(mode);
    }


    @EventHandler
    public void onTick(TickEvent event) {
        if (mode.isMode("Vanilla")) {
        }
        if (mode.isMode("NCP")) {
        }
        if (mode.isMode("Volcanware")) {
        }
        if (mode.isMode("Dev")) {

        }
    }
}
