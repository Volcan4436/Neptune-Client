package neptune.features.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.Neptune;
import neptune.module.Mod;
import neptune.module.settings.ModeSetting;
import neptune.module.settings.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class ReverseStep extends Mod {
    private final ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Vanilla");

    public ReverseStep() {
        super("ReverseStep", "cmon bruh", Category.MOVEMENT);
        addSettings(mode);
    }
}

