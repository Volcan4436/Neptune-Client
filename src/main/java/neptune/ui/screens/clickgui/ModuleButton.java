package neptune.ui.screens.clickgui;

import neptune.module.api.Flags;
import neptune.utils.MinecraftInterface;
import neptune.utils.MiscUtils;
import neptune.utils.RenderUtils;
import net.minecraft.client.gui.DrawContext;
import neptune.module.api.Module;
import neptune.setting.impl.BooleanSetting;
import neptune.setting.impl.ModeSetting;
import neptune.setting.impl.NumberSetting;
import neptune.setting.Setting;
import neptune.ui.screens.clickgui.setting.CheckBox;
import neptune.ui.screens.clickgui.setting.Component;
import neptune.ui.screens.clickgui.setting.ModeBox;
import neptune.ui.screens.clickgui.setting.Slider;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleButton implements MinecraftInterface {
    final List<Component> components = new ArrayList<>();
    private final Module module;
    public final Frame parent;
    public int offset;
    boolean extended;

    public ModuleButton(Module module, Frame parent, int offset) {
        this.module = module;
        this.parent = parent;
        this.offset = offset;

        int setOffset = parent.height;
        for (Setting setting : module.getSettings()) {
            if (setting instanceof BooleanSetting booleanSetting) {
                components.add(new CheckBox(booleanSetting, this, setOffset));
            } else if (setting instanceof ModeSetting modeSetting) {
                components.add(new ModeBox(modeSetting, this, setOffset));
            } else if (setting instanceof NumberSetting numberSetting) {
                components.add(new Slider(numberSetting, this, setOffset));
            }
            setOffset += parent.height;
        }
    }

    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, module.isToggled() ? new Color(60,60,60,200).getRGB() : new Color(20, 20, 20, 200).getRGB());

        if (RenderUtils.isHovered(mouseX, mouseY, parent.x, parent.y + offset, parent.width, parent.height))
            context.fill(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, new Color(26, 54, 80, 255).getRGB());

        String name = module.getName();
        if (MiscUtils.contains(module.getFlags(), Flags.UNSAFE))
            name += " (!)";

        context.drawText(mc.textRenderer, name, parent.x + 4, parent.y + offset + 2, module.isToggled() ? Color.WHITE.getRGB() : Color.GRAY.getRGB(), !module.isToggled());

        if (!module.getSettings().isEmpty())
            context.drawText(mc.textRenderer, extended ? "+" : "-", parent.x + parent.width - 3 - mc.textRenderer.getWidth("+"), parent.y + offset + 3, -1, !module.isToggled());

        if (module.isToggled())
            context.fill(parent.x, parent.y + offset,parent.x + 1, parent.y + offset + parent.height, new Color(0, 190, 255).getRGB());

        if (extended)
            for (Component component : components)
               if (component.setting.isVisible())
                   component.render(context, mouseX, mouseY, delta);
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (RenderUtils.isHovered(mouseX, mouseY, parent.x, parent.y + offset, parent.width, parent.height)) {
            if (button == 0)
                module.toggle();
            else if (button == 1) {
                extended = !extended;
                parent.updateButtons();
            }
        }

        for (Component component : components)
            component.mouseClicked(mouseX, mouseY, button);
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        for (Component component : components)
            component.mouseReleased(mouseX, mouseY, button);
    }
}