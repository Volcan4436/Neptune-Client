package neptune.ui.screens.clickgui.setting;

import neptune.setting.BooleanSetting;
import neptune.setting.Setting;
import neptune.ui.screens.clickgui.ModuleButton;
import neptune.utils.MinecraftInterface;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class CheckBox extends Component implements MinecraftInterface {

    private BooleanSetting boolSet = (BooleanSetting)setting;

    public CheckBox(Setting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.boolSet = (BooleanSetting)setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(53, 80, 107, 255).getRGB());
        int textOffset = ((parent.parent.height /2) - mc.textRenderer.fontHeight / 2);
        context.drawTextWithShadow(mc.textRenderer, boolSet.getName() + ": " + boolSet.isEnabled(), parent.parent.x + textOffset, parent.parent.y + parent.offset + offset + textOffset, -1);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0) {
            boolSet.toggle();
        }
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {}
}
