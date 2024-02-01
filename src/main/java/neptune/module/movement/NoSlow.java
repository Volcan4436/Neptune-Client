package neptune.module.movement;

import neptune.module.Mod;
import neptune.setting.BooleanSetting;

public class NoSlow extends Mod {

    boolean eating = true;
    private boolean usedTimer;
    private final BooleanSetting items = new BooleanSetting("Items", true);

    public NoSlow() {
        super("NoSlow", "bruh", Category.MOVEMENT);
        addSetting(items);
    }

    public boolean eating() {
        return false;
    }
}
