package me.neptune.features.modules.client;

import me.neptune.Neptune;
import me.neptune.event.impl.Render2DEvent;
import me.neptune.features.modules.Module;

public class HudModule extends Module {
    public HudModule() {
        super("Hud", "hud", Category.CLIENT, true, false, false);
    }

    @Override public void onRender2D(Render2DEvent event) {
        event.getContext().drawTextWithShadow(
                mc.textRenderer,
                Neptune.NAME + " " + Neptune.VERSION,
                2, 2,
                -1
        );
    }
}
