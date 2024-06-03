package neptune.ui.screens.clickgui;

import neptune.module.api.Category;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class DropDownGUI extends Screen {
    private final List<Frame> frames = new ArrayList<>();

    public DropDownGUI() {
        super(Text.literal("ClickGUI"));

        int width = 120;
        int x = 40;
        for (Category category : Category.values()) {
            frames.add(new Frame(category, x, 30, width, 15));
            x += width + 30;
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        for (Frame frame : frames) {
            frame.render(context, mouseX, mouseY, delta);
            frame.updatePosition(mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Frame frame : frames)
            frame.mouseClicked(mouseX, mouseY, button);

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Frame frame : frames)
            frame.mouseReleased(mouseX, mouseY, button);

        return super.mouseReleased(mouseX, mouseY, button);
    }
}