package neptune.ui.screens.clickgui;

import neptune.utils.MinecraftInterface;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import neptune.module.Mod;
import neptune.module.settings.BooleanSetting;
import neptune.module.settings.ModeSetting;
import neptune.module.settings.NumberSetting;
import neptune.module.settings.Setting;
import neptune.ui.screens.clickgui.setting.CheckBox;
import neptune.ui.screens.clickgui.setting.Component;
import neptune.ui.screens.clickgui.setting.ModeBox;
import neptune.ui.screens.clickgui.setting.Slider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleButton implements MinecraftInterface {



    public Mod module;
    public Frame parent;
    public int offset;
    public List<Component> components;
    public boolean extended;

    public ModuleButton(Mod module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;
        this.components = new ArrayList<>();
        this.extended = false;

        int setOffset = parent.height;
        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting) {
                components.add(new CheckBox(setting, this, setOffset));
            } else if (setting instanceof ModeSetting) {
                components.add(new ModeBox(setting, this, setOffset));
            } else if (setting instanceof NumberSetting) {
                components.add(new Slider(setting, this, setOffset));
            }
            setOffset += parent.height;
        }
    }
    public static int getRainbow(float sat, float bri, double speed, int offset) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + offset) / speed) % 360;
        return 0xff000000 | MathHelper.hsvToRgb((float) (rainbowState / 360.0), sat, bri);
    }
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(0, 0, 0, 200).getRGB());
        //Draw a white line above the module
        context.fill(parent.x, parent.y + offset - 1, parent.x + parent.width, parent.y + offset, new Color(0, 140, 255).getRGB());
        if (isHovered(mouseX, mouseY)) context.fill(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(26, 54, 80, 255).getRGB());
        context.drawTextWithShadow(mc.textRenderer, module.getName(), parent.x + 2, parent.y + offset + 2, module.isEnabled() ? Color.RED.getRGB() : Color.WHITE.getRGB());

        if (extended) {
            for (Component component : components) {
                component.render(context, mouseX, mouseY, delta);
            }
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY)) {
            if (button == 0) {
                module.toggle();
            }
            else if (button == 1) {
                extended = !extended;
                parent.updateButtons();

            }
        }

        for (Component component : components) {
            component.mouseClicked(mouseX, mouseY, button);
        }
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (Component component : components) {
            component.mouseReleased(mouseX, mouseY, button);
        }
    }
    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.x && mouseX < parent.x + parent.width && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.height;
    }
}
