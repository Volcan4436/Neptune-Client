package neptune.features.movement;

import meteordevelopment.orbit.EventHandler;
import neptune.event.events.TickEvent;
import neptune.mixins.mixininterface.IVec3d;
import neptune.module.Mod;
import neptune.module.settings.NumberSetting;
import net.minecraft.block.BedBlock;
import net.minecraft.util.math.BlockPos;

public class ReverseStep extends Mod {

    private final NumberSetting fallSpeed = new NumberSetting("Fall Speed", 0, 5, 3, 0.1);
    private final NumberSetting fallDistance = new NumberSetting("Fall Distance", 0, 5, 3, 0.1);

    public ReverseStep() {
        super("ReverseStep", "cmon bruh", Category.MOVEMENT);
        addSettings(fallDistance, fallSpeed);
    }
}
