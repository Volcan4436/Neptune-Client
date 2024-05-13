package neptune.ui.screens.clickgui.setting;

import neptune.setting.Setting;
import neptune.ui.screens.clickgui.ModuleButton;
import neptune.utils.MinecraftInterface;
import net.minecraft.client.gui.DrawContext;

public abstract class Component implements MinecraftInterface {

    public Setting setting;
    public ModuleButton parent;
    public int offset;

    public Component(Setting setting, ModuleButton parent, int offset) {
        this.setting = setting;
        this.parent = parent;
        this.offset = offset;

    }
    public abstract void render(DrawContext context, int mouseX, int mouseY, float delta);
    public abstract void mouseClicked(double mouseX, double mouseY, int button);
    public abstract void mouseReleased(double mouseX, double mouseY, int button);

    public boolean isHovered(double mouseX, double mouseY) {
        return mouseX > parent.parent.x && mouseX < parent.parent.x + parent.parent.width && mouseY > parent.parent.y + parent.offset + offset && mouseY < parent.parent.y + parent.offset + offset + parent.parent.height;
    }
}
