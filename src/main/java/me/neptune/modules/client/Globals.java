package me.neptune.modules.client;

import me.neptune.api.module.Category;
import me.neptune.api.module.Module;
import me.neptune.api.setting.Setting;
import me.neptune.api.setting.types.BoolSetting;

public class Globals extends Module
{
    public static Globals INSTANCE;

    public Globals()
    {
        super("Globals", Category.CLIENT);
        INSTANCE = this;
    }

    public static Globals getInstance(){
        return INSTANCE != null ? INSTANCE : new Globals();
    }
}
