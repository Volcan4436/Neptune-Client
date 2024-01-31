package neptune.ui.screens.clickgui;

import neptune.module.Mod;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class ClickGUI extends Screen {

    private final List<Frame> frames;
    protected ClickGUI() {
        super(Text.literal("ClickGUI"));

        frames = new ArrayList<>();
        int x = 20;
        for (Mod.Category category : Mod.Category.values()) {
            frames.add(new Frame(category, x, 30, 100, 15));
            x += 120;
        }
    }
    public static final ClickGUI INSTANCE = new ClickGUI();

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        for (Frame frame : frames) {
            frame.render(context, mouseX, mouseY, delta);
            frame.updatePosition(mouseX, mouseY);
        }
        super.render(context, mouseX, mouseY, delta);
    }
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseClicked(mouseX, mouseY, button);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for (Frame frame : frames) {
            frame.mouseReleased(mouseX, mouseY, button);
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {}

    @Override
    public void renderInGameBackground(DrawContext context) {}

    @Override
    public void renderBackgroundTexture(DrawContext context) {}
}
