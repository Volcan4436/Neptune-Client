package neptune.utils;

import net.minecraft.util.math.MathHelper;

public class RenderUtils implements MinecraftInterface {

    public static int getRainbow(float sat, float bri, double speed, int offset) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed) % 360;
        return 0xff000000 | MathHelper.hsvToRgb((float) (rainbowState / 360.0), sat, bri);
    }

    public static boolean isHovered(double mouseX, double mouseY, int x, int y, int x1, int y2) {
        return mouseX >= x && mouseX <= x + x1 && mouseY >= y && mouseY <= y + y2;
    }

    public static boolean isHovered(double mouseX, double mouseY, float x, float y, float x1, float y2) {
        return mouseX >= x && mouseX <= x + x1 && mouseY >= y && mouseY <= y + y2;
    }

    public static boolean isHovered(double mouseX, double mouseY, double x, double y, double x1, double y2) {
        return mouseX >= x && mouseX <= x + x1 && mouseY >= y && mouseY <= y + y2;
    }
}