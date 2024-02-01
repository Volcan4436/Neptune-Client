package neptune.features.render;

import neptune.module.Mod;
import neptune.module.settings.ModeSetting;
import neptune.module.settings.NumberSetting;

public class FullBright extends Mod {

    private final ModeSetting mode = new ModeSetting("Mode", "Gamma", "Gamma", "Luminance");
    private final NumberSetting minimumLightLevel = new NumberSetting("Minimum Light Level", 0, 15, 5, 0.1);

    public FullBright() {
        super("FullBright", "Makes dark thangs lighter!!", Category.RENDER);
        addSetting(mode);
        minimumLightLevel.setDependency(() -> mode.getMode().equals("Gamma"));
        addSetting(minimumLightLevel);
    }
}
