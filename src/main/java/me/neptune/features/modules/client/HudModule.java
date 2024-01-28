package me.neptune.features.modules.client;

import me.neptune.Neptune;
import me.neptune.event.impl.Render2DEvent;
import me.neptune.features.modules.Module;
import me.neptune.features.settings.Setting;

public class HudModule extends Module {

    Setting<Boolean> watermark = this.register(new Setting<>("Watermark", true));
    public HudModule() {
        super("Hud", "hud", Category.CLIENT, true, false, false);
    }

    @Override public void onRender2D(Render2DEvent event) {
        if (this.watermark.getValue().booleanValue())
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                Neptune.NAME + " " + Neptune.VERSION,
                2, 2,
                -1
        );
    }
}
