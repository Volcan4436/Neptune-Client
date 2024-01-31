package neptune.features.combat;

import neptune.module.Mod;
import neptune.module.settings.ModeSetting;

public class KeyPearlPhase extends Mod {

    private final ModeSetting mode = new ModeSetting("Mode", "Phase", "Phase");
    private final ModeSetting switchmode = new ModeSetting("Switch", "Silent", "Normal", "Silent");

    public KeyPearlPhase() {
        super("KeyPearlPhase", "Press your keybind to pearl.", Category.COMBAT);
        addSettings(mode, switchmode);
    }

}
