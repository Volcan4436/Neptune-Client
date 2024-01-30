package neptune.ui;

import neptune.Module.Mod;
import neptune.Module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class Hud {

    private static int color = 0xFF46BE14;

    private static MinecraftClient mc = MinecraftClient.getInstance();

    public static void setColour(int colour) {
        System.out.println("Setting colour to " + colour);
        color = colour;
    }

    public static int getRainbow(float sat, float bri, double speed, int offset) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed) % 360;
        return 0xff000000 | MathHelper.hsvToRgb((float) (rainbowState / 360.0), sat, bri);
    }
    public static void render(MatrixStack matrices, float tickDelta) {
        renderArrayList(matrices);
        mc.textRenderer.drawWithShadow(matrices, "Client", 4, 2, color);
        DrawableHelper.fill(matrices, 6 + mc.textRenderer.getWidth("Client"), 2, 4 + mc.textRenderer.getWidth("Client") + 1, 2 + mc.textRenderer.fontHeight, color);
    }
    public static void renderArrayList(MatrixStack matrices) {

        int xOffset = -5;
        int yOffset = 5;
        int index = 0;
        List<Mod> enabled = ModuleManager.INSTANCE.getEnabledModules();
        int sWidth = mc.getWindow().getScaledWidth();
        int sHeight = mc.getWindow().getScaledHeight();
        int lastWidth;
        int fHeight = mc.textRenderer.fontHeight;
        int fromY = (fHeight - 1) * (index) + 1;
        int toX = sWidth - 2;
        int toY = (fHeight - 1) * (index) + fHeight;
        enabled.sort(Comparator.comparingInt(m -> (int)mc.textRenderer.getWidth(((Mod)m).getDisplayName())).reversed());

        for (Mod mod : enabled) {
            DrawableHelper.fill(matrices, (sWidth + 100) - mc.textRenderer.getWidth(mod.getDisplayName()) - 1, 9 + (index * mc.textRenderer.fontHeight), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()) - 2, 10 + (index * mc.textRenderer.fontHeight - 1) + mc.textRenderer.fontHeight, 0x80000000);
            DrawableHelper.fill(matrices, (sWidth + 100) - mc.textRenderer.getWidth(mod.getDisplayName()) - 1, 9 + (index * mc.textRenderer.fontHeight), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()) - 2, 10 + (index * mc.textRenderer.fontHeight) + mc.textRenderer.fontHeight, 1);

            mc.textRenderer.drawWithShadow(matrices, mod.getDisplayName(), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()), 10 + (index * mc.textRenderer.fontHeight), color);

            DrawableHelper.fill(matrices, (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()) - 1, 9 + (index * mc.textRenderer.fontHeight), (sWidth - 4) - mc.textRenderer.getWidth(mod.getDisplayName()) - 2, 9 + (index * mc.textRenderer.fontHeight) + mc.textRenderer.fontHeight, Color.GRAY.getRGB());
            index++;
        }
    }
}
