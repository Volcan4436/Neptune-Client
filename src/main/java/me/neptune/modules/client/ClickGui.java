package me.neptune.modules.client;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.screen.click.NeptuneClickGui;

public class ClickGui extends Module
{
    public ClickGui() {
        super("ClickGui", Category.CLIENT);
        registerDescription("epic gui");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        mc.setScreen(new NeptuneClickGui());
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
