package neptune.mixins;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

import static neptune.utils.MinecraftInterface.mc;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Text title) {
        super(title);
    }


    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo info) {
        float y = 2;
        float y2 = y + textRenderer.fontHeight + y;
        String space = " ";
        int spaceLength = textRenderer.getWidth(space);

        context.drawTextWithShadow(mc.textRenderer, "Neptune Client", 4, (int) y, 0x5271ff);
        context.drawTextWithShadow(mc.textRenderer, "by The Volcanware Team", 4, (int) y + 8, Color.white.getRGB());
    }
/*    @Inject(at = @At("RETURN"), method = "initWidgetsNormal")
    private void addModsButton(int y, int spacingY, CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("bleed.clickgui"), (button) -> {
            this.client.setScreen(ClickGUI.INSTANCE);
        }).dimensions(this.width / 2 - 200 +252, y, 50, 200).build());
    }*/
}

