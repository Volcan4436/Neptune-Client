package me.neptune.api.setting.types;

import me.neptune.api.setting.Setting;

public class BoolSetting extends Setting<Boolean>
{
    public BoolSetting(String name, boolean value)
    {
        super(name, value);
    }
}
