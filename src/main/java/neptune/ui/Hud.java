package neptune.ui;

import neptune.Neptune;
import neptune.module.Mod;
import neptune.utils.MinecraftInterface;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

/*public class Hud implements MinecraftInterface {

    private static int color = 0x25b5d2;

    public static void setColour(int colour) {
        System.out.println("Setting colour to " + colour);
        color = colour;
    }

    public static int getRainbow(float sat, float bri, double speed, int offset) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed) % 360;
        return 0xff000000 | MathHelper.hsvToRgb((float) (rainbowState / 360.0), sat, bri);
    }

    public static void render(DrawContext context, float tickDelta) {
        renderArrayList(context);
        context.drawTextWithShadow(mc.textRenderer, "Client", 4, 2, color);
        context.fill(6 + mc.textRenderer.getWidth("Client"), 2, 4 + mc.textRenderer.getWidth("Client") + 1, 2 + mc.textRenderer.fontHeight, color);
    }

    public static void renderArrayList(DrawContext context) {

        int xOffset = -5;
        int yOffset = 5;
        int index = 0;
        List<Mod> enabled = Neptune.getInstance().getModuleManager().getEnabledModules();
        int sWidth = mc.getWindow().getScaledWidth();
        int sHeight = mc.getWindow().getScaledHeight();
        int lastWidth;
        int fHeight = mc.textRenderer.fontHeight;
        int fromY = (fHeight - 1) * (index) + 1;
        int toX = sWidth - 2;
        int toY = (fHeight - 1) * (index) + fHeight;
        enabled.sort(Comparator.comparingInt(m -> (int)mc.textRenderer.getWidth(((Mod)m).getDisplayName())).reversed());

        for (Mod mod : enabled) {
            context.fill((sWidth + 100) - mc.textRenderer.getWidth(mod.getDisplayName()) - 1, 9 + (index * mc.textRenderer.fontHeight), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()) - 2, 10 + (index * mc.textRenderer.fontHeight - 1) + mc.textRenderer.fontHeight, 0x80000000);
            context.fill((sWidth + 100) - mc.textRenderer.getWidth(mod.getDisplayName()) - 1, 9 + (index * mc.textRenderer.fontHeight), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()) - 2, 10 + (index * mc.textRenderer.fontHeight) + mc.textRenderer.fontHeight, 1);

            context.drawTextWithShadow(mc.textRenderer, mod.getDisplayName(), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()), 10 + (index * mc.textRenderer.fontHeight), color);

            context.fill((sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()) - 1, 9 + (index * mc.textRenderer.fontHeight), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()) - 2, 9 + (index * mc.textRenderer.fontHeight) + mc.textRenderer.fontHeight, Color.GRAY.getRGB());
            index++;
        }
    }
}*/
