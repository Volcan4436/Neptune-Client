package neptune.ui.screens.clickgui.setting;

import neptune.module.settings.ModeSetting;
import neptune.module.settings.Setting;
import neptune.ui.screens.clickgui.ModuleButton;
import neptune.utils.MinecraftInterface;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class ModeBox extends Component implements MinecraftInterface {

    private ModeSetting modeSet = (ModeSetting) setting;

    public ModeBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.modeSet = (ModeSetting) setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(53, 80, 107, 255).getRGB());
        int textOffset = ((parent.parent.height /2) - mc.textRenderer.fontHeight / 2);
        context.drawTextWithShadow(mc.textRenderer, modeSet.getName() + ": " + modeSet.getMode(), parent.parent.x + textOffset, parent.parent.y + parent.offset + offset + textOffset, -1);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            modeSet.cycle();
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {}
}
