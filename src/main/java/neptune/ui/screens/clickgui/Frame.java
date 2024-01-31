package neptune.ui.screens.clickgui;

import neptune.Neptune;
import neptune.utils.MinecraftInterface;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import neptune.module.Mod;
import neptune.ui.screens.clickgui.setting.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frame implements MinecraftInterface {

    public int x, y, width, height, dragX, dragY;
    public Mod.Category category;
    public boolean dragging, extended;

    private final List<ModuleButton> buttons;

    public static int getRainbow(float sat, float bri, double speed, int offset) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed) % 360;
        return 0xff000000 | MathHelper.hsvToRgb((float) (rainbowState / 360.0), sat, bri);
    }

    public Frame(Mod.Category category, int x, int y, int width, int height) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dragging = false;
        this.extended = false;

        buttons = new ArrayList<>();

        int offset = height;
        for (Mod mod : Neptune.getInstance().getModuleManager().getModulesInCategory(category)) {
            buttons.add(new ModuleButton(mod, this, offset));
            offset += height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(x, y, x + width, y + height, new Color(0, 188, 255).getRGB());
        //draw a white border around the frame
        context.fill(x, y, x + width, y + 1, Color.white.getRGB());
        context.fill(x, y, x + 1, y + height, Color.white.getRGB());
        context.fill(x + width - 1, y, x + width, y + height, Color.white.getRGB());
        context.fill(x, y + height - 1, x + width, y + height, Color.white.getRGB());
        int offset = + ((height - mc.textRenderer.fontHeight) / 2);
        context.drawTextWithShadow(mc.textRenderer, category.name, x + 2, y + 2, -1);
        context.drawTextWithShadow(mc.textRenderer, extended ? "[-]" : "[+]", x + width - 2 - mc.textRenderer.getWidth("[+]"), y + 2, -2);

        if (extended) {
            for (ModuleButton button : buttons) {
                button.render(context, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                dragging = true;
                dragX = (int) (mouseX - x);
                dragY = (int) (mouseY - y);
            }
            else if (button == 1) {
                extended = !extended;
                updateButtons();
            }
        }
        if (extended) {
            for (ModuleButton mb : buttons) {
                mb.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

   public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && dragging) dragging = false;

        for (ModuleButton mb : buttons) {
            mb.mouseReleased(mouseX, mouseY, button);
        }
    }
    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public void updatePosition(double mouseX, double mouseY) {
        if (dragging) {
            x = (int)mouseX - dragX;
            y = (int)mouseY - dragY;
        }
    }

    public void updateButtons() {
        int offset = height;

        for (ModuleButton button : buttons) {
            button.offset = offset;
            offset += height;

            if (button.extended) {
                for (Component component : button.components) {
                    if (component.setting.isVisible()) offset += height;
                }
            }
        }
    }
}
