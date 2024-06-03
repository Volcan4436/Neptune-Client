package neptune.module.impl.visuals;

import io.github.nevalackin.radbus.Listen;
import neptune.Neptune;
import neptune.event.impl.render.RenderEvent;
import neptune.module.ModuleManager;
import neptune.module.api.Module;
import neptune.module.api.ModuleInfo;
import neptune.setting.impl.BooleanSetting;
import neptune.utils.NetworkUtils;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ModuleInfo(description = "Hud")
public class HUD extends Module {
    private final BooleanSetting watermark = new BooleanSetting("Watermark", true);
    private final BooleanSetting arraylist = new BooleanSetting("ArrayList", true);
    private final BooleanSetting lag = new BooleanSetting("Lag Notifier");
    private final BooleanSetting pinghud = new BooleanSetting("Ping", true);
    private static int color = 0x25b5d2;

    public HUD() {
        addSettings(watermark, arraylist, lag, pinghud);
    }

    @Listen
    public void onRender(RenderEvent event) {
        if (event.is3D()) return;

        DrawContext context = event.getContext();
        if (watermark.isEnabled()) {
            context.drawTextWithShadow(mc.textRenderer, "Neptune " + Neptune.VERSION, 4, 2, color);
            context.fill(6 + mc.textRenderer.getWidth("Neptune"), 2, 4 + mc.textRenderer.getWidth("Neptune") + 1, 2 + mc.textRenderer.fontHeight, color);
        }
        renderArrayList(context);
    }

    public void renderArrayList(DrawContext context) {
        if (!arraylist.isEnabled()) return;

        int xOffset = -5;
        int yOffset = 5;
        int index = 0;
        int sWidth = mc.getWindow().getScaledWidth();
        int sHeight = mc.getWindow().getScaledHeight();
        int lastWidth;
        int fHeight = mc.textRenderer.fontHeight;
        int fromY = (fHeight - 1) * (index) + 1;
        int toX = sWidth - 2;
        int toY = (fHeight - 1) * (index) + fHeight;

        List<Module> enabled = new ArrayList<>(ModuleManager.getInstance().getEnabledModules());
        enabled.sort(Comparator.comparing(Module::getName));

        for (Module mod : enabled) {
            context.fill((sWidth + 100) - mc.textRenderer.getWidth(mod.getName()) - 1, 9 + (index * mc.textRenderer.fontHeight), (sWidth - 4) - mc.textRenderer.getWidth(mod.getName()) - 2, 10 + (index * mc.textRenderer.fontHeight - 1) + mc.textRenderer.fontHeight, 0x80000000);
            context.fill((sWidth + 100) - mc.textRenderer.getWidth(mod.getName()) - 1, 9 + (index * mc.textRenderer.fontHeight), (sWidth - 4) - mc.textRenderer.getWidth(mod.getName()) - 2, 10 + (index * mc.textRenderer.fontHeight) + mc.textRenderer.fontHeight, 1);

            context.drawTextWithShadow(mc.textRenderer, mod.getName(), (sWidth - 4) - mc.textRenderer.getWidth(mod.getName()), 10 + (index * mc.textRenderer.fontHeight), color);

            context.fill((sWidth - 4) - mc.textRenderer.getWidth(mod.getName()) - 1, 9 + (index * mc.textRenderer.fontHeight), (sWidth - 4) - mc.textRenderer.getWidth(mod.getName()) - 2, 9 + (index * mc.textRenderer.fontHeight) + mc.textRenderer.fontHeight, Color.GRAY.getRGB());
            index++;
        }
    }

    public  void renderPingHud(DrawContext context) {
        if (pinghud.isEnabled()) {
            context.drawTextWithShadow(mc.textRenderer, "Ping " + NetworkUtils.getPing(), 50, 10, color);
            context.fill(6 + mc.textRenderer.getWidth("Ping"), 2, 4 + mc.textRenderer.getWidth("Ping") + 1, 2 + mc.textRenderer.fontHeight, color);
        }
    }
}