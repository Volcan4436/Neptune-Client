package neptune.mixins;

import neptune.Neptune;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

import static neptune.utils.MinecraftInterface.mc;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo info) {
        int y = 2;
        context.drawTextWithShadow(mc.textRenderer, "Neptune Client " + Neptune.VERSION, 4, y, 0x5271ff);
        context.drawTextWithShadow(mc.textRenderer, "by: Volcanware Team", 4, y + 8, Color.white.getRGB());
    }
}