package neptune.ui.screens.clickgui.setting;

import neptune.setting.impl.NumberSetting;
import neptune.setting.Setting;
import neptune.ui.screens.clickgui.ModuleButton;
import neptune.utils.MinecraftInterface;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Slider extends Component {
    private final NumberSetting numSet;
    private boolean sliding = false;

    public Slider(NumberSetting setting, ModuleButton parent, int offset) {
        super(setting, parent, offset);
        this.numSet = setting;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        int renderWidth = (int) (parent.parent.width * (numSet.getValue() - numSet.getMinimum()) / (numSet.getMaximum() - numSet.getMinimum()));
        context.fill(parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(53, 80, 107, 255).getRGB());

        double diff = Math.min(parent.parent.width, Math.max(0, mouseX - parent.parent.x));
        context.fill(parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + renderWidth, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(255, 255, 255, 200).getRGB());

        if (sliding) {
            if (diff == 0)
                numSet.setValue(numSet.getMinimum());
            else
                numSet.setValue(roundToPlace((diff / parent.parent.width) * (numSet.getMaximum() - numSet.getMinimum()) + numSet.getMinimum(), 2));
        }
        int textOffset = ((parent.parent.height / 2) - mc.textRenderer.fontHeight / 2);
        context.drawTextWithShadow(mc.textRenderer, numSet.getName() + ": " + roundToPlace(numSet.getValue(), 2), parent.parent.x + textOffset, parent.parent.y + parent.offset + offset + textOffset, -1);
    }

    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHovered(mouseX, mouseY) && button == 0)
            sliding = true;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        sliding = false;
    }

    private double roundToPlace(double value, int place) {
        if (place < 0)
            return value;

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(place, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}