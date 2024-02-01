package neptune.module.render;

import neptune.module.Mod;
import neptune.setting.ModeSetting;
import neptune.setting.NumberSetting;

public class FullBright extends Mod {

    public static FullBright Instance;
    private double previousGammaValue = 0.0;

    private final ModeSetting modeSetting = new ModeSetting("Mode", "Gamma", "Gamma", "Luminance");
    private final NumberSetting minimumLightLevelSetting = new NumberSetting("Minimum Light Level", 0, 15, 5, 0.1);

    public FullBright() {
        super("FullBright", "Makes dark things lighter!!", Category.RENDER);
        addSetting(modeSetting);
        minimumLightLevelSetting.setDependency(() -> modeSetting.getMode().equals("Gamma"));
        addSetting(minimumLightLevelSetting);
        Instance = this;
    }

    @Override
    public void onEnable() {
        if (modeSetting.isMode("Gamma")) {
            //mc.player.addStatusEffect(new StatusEffectInstance(StatusEffect.byRawId(16), 5200, 1));

        }
    }

    @Override
    public void onDisable() {
        if (modeSetting.isMode("Gamma")) {

        }
    }
}
