package neptune.features.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.Neptune;
import neptune.event.events.TickEvent;
import neptune.module.Mod;
import neptune.module.settings.BooleanSetting;
import neptune.module.settings.ModeSetting;
import neptune.module.settings.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class ReverseStep extends Mod {

    private final BooleanSetting strict = new BooleanSetting("Strict", false);
    private final BooleanSetting timer = new BooleanSetting("Timer", false);
    private final NumberSetting fallDistance = new NumberSetting("Fall Distance", 0, 5, 3, 0.1);

    public ReverseStep() {
        super("ReverseStep", "cmon bruh", Category.MOVEMENT);
        addSettings(strict, timer, fallDistance);
    }
}

