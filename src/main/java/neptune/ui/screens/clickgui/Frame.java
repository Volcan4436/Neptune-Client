package neptune.ui.screens.clickgui;

import neptune.module.ModuleManager;
import neptune.module.api.Category;
import neptune.utils.MinecraftInterface;
import neptune.utils.render.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import neptune.module.api.Module;
import neptune.ui.screens.clickgui.setting.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Frame implements MinecraftInterface {
    private final List<ModuleButton> modules = new ArrayList<>();
    private final Category category;

    public final int width, height;
    public int x, y, dragX, dragY;
    private boolean dragging, extended;

    public Frame(Category category, int x, int y, int width, int height) {
        this.category = category;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        int offset = height;
        for (Module mod : ModuleManager.getInstance().getModulesInCategory(category)) {
            modules.add(new ModuleButton(mod, this, offset));
            offset += height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(x, y, x + width, y + height, new Color(82,113,255,255).getRGB());

        context.drawTextWithShadow(mc.textRenderer, category.getName(), x + 2, y + 2, -1);
        context.drawTextWithShadow(mc.textRenderer, extended ? "+" : "-", x + width - 3 - mc.textRenderer.getWidth("+"), y + 3, -2);

        if (extended)
            for (ModuleButton module : modules)
                module.render(context, mouseX, mouseY, delta);
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (RenderUtils.isHovered(mouseX, mouseY, x, y, width, height)) {
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

        if (extended)
            for (ModuleButton module : modules)
                module.mouseClicked(mouseX, mouseY, button);
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0 && dragging)
            dragging = false;

        for (ModuleButton module : modules)
            module.mouseReleased(mouseX, mouseY, button);
    }

    public void updatePosition(double mouseX, double mouseY) {
        if (dragging) {
            x = (int) mouseX - dragX;
            y = (int) mouseY - dragY;
        }
    }

    public void updateButtons() {
        int offset = height;

        for (ModuleButton button : modules) {
            button.offset = offset;
            offset += height;

            if (button.extended)
                for (Component component : button.components)
                    if (component.setting.isVisible())
                        offset += height;
        }
    }
}