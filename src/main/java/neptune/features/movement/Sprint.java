package neptune.features.movement;

import com.google.common.eventbus.Subscribe;
import neptune.event.EventManager;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.module.settings.ModeSetting;

public class Sprint extends Mod {

    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla", "Rage", "Omni");

    public Sprint() {
        super("Sprint", "Automatically lets you sprint.", Category.MOVEMENT);
        addSetting(mode);
    }

    private final EventManager.EventListener<TickEvent> TickEventListener = e -> {
        if (mode.isMode("Omni")){
            if (mc.options.forwardKey.isPressed()) {
                mc.player.setSprinting(true);
            }
            if (mc.options.rightKey.isPressed()) {
                mc.player.setSprinting(true);
            }
            if (mc.options.leftKey.isPressed()) {
                mc.player.setSprinting(true);
            }
            if (mc.options.backKey.isPressed()) {
                mc.player.setSprinting(true);
            }
        }

        if (mode.isMode("Rage")){
            mc.player.setSprinting(true);
        }
    };

    @Subscribe
    public void onTick() {
    }

    @Override
    public boolean onEnable() {
        return false;
    }

    @Override
    public void onDisable() {
    }
}
