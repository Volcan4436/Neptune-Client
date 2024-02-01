package neptune.features.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.Neptune;
import neptune.event.events.EventMove;
import neptune.module.Mod;
import neptune.module.settings.BooleanSetting;
import neptune.module.settings.ModeSetting;
import net.minecraft.entity.effect.StatusEffects;

import java.util.Objects;

public class Speed extends Mod {
    private final ModeSetting mode = new ModeSetting("Mode", "NCP", "NCP", "StrictStrafe");
    private final BooleanSetting useTimer = new BooleanSetting("Use Timer", false);

    public Speed() {
        super("Speed", "Automatically speeds you up.", Category.MOVEMENT);
        addSetting(mode);
        addSetting(useTimer);
    }
}
