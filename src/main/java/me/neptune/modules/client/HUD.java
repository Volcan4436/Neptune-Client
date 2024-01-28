package me.neptune.modules.client;

import com.google.common.eventbus.Subscribe;
import me.neptune.Neptune;
import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.setting.Setting;
import me.neptune.api.setting.types.BoolSetting;
import me.neptune.events.render.Render2DEvent;
import me.neptune.managers.ManagerHelper;
import me.neptune.util.minecraft.ChatUtil;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.Window;
import net.minecraft.util.Formatting;

import java.text.DecimalFormat;

public class HUD extends Module
{
    protected final Setting<Boolean> watermark =
            add(new BoolSetting("Watermark", true));

    protected final Setting<Boolean> moduleList =
            add(new BoolSetting("Modules", true));

    protected final Setting<Boolean> coordinates =
            add(new BoolSetting("Coordinates", true));
    protected final Setting<Boolean> otherDimensions =
            add(new BoolSetting("OtherDimensions", true));

    protected Window wnd = null;

    public HUD() {
        super("HUD", Category.CLIENT);
        registerDescription("Displays various information on your Screen.");
        this.setEnabled(true);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Subscribe
    public void onRender2D(Render2DEvent event)
    {
        if(this.wnd == null)
            this.wnd = mc.getWindow();

        renderText(event.getContext());
    }

    public void renderText(DrawContext ctx)
    {
        int baseY = -8; // CHINESE but this is a quick and hacky fix for this

        if (watermark.getValue())
            ctx.drawTextWithShadow(mc.textRenderer,
                    Neptune.NAME + " - " + Neptune.VER_STRING,
                    2, 2, 0xffffff);

        if (moduleList.getValue())
        {
            for (Module module : ManagerHelper.MODULES.getRegistered())
            {
                if (module.isEnabled())
                {
                    ctx.drawTextWithShadow(mc.textRenderer,
                            module.getName() + (module.getHudData() != null
                                    ? Formatting.GRAY + "[" + Formatting.WHITE + module.getHudData() + Formatting.GRAY + "]"
                                    : ""),
                            wnd.getScaledWidth() - mc.textRenderer.getWidth(module.isEnabled() ? module.getName() + (module.getHudData() != null ? module.getHudData() + 2 : ".") : ""),
                            baseY += 10, 0xffffff);
                }
            }
        }

        if (coordinates.getValue())
        {
            if(mc.player == null) return;

            DecimalFormat df = new DecimalFormat("###.#");
            String coords = Formatting.GRAY + "XYZ " + Formatting.WHITE + (df.format(mc.player.getX()) +
                    Formatting.GRAY + ", " +
                    Formatting.WHITE +df.format(mc.player.getY()) +
                    Formatting.GRAY + ", " + Formatting.WHITE + df.format(mc.player.getZ())) +
                    (otherDimensions.getValue() ? (Formatting.GRAY + " [" + Formatting.WHITE + (df.format(mc.player.getX() * 8) + Formatting.GRAY + ", " + Formatting.WHITE + df.format(mc.player.getZ() * 8) + Formatting.GRAY + "]")) : "");

            ctx.drawTextWithShadow(mc.textRenderer, coords, 2, wnd.getScaledHeight() - 10, 0xffffff);
        }

    }
}
