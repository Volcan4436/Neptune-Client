package neptune.setting.impl;

import neptune.setting.Setting;

public class BooleanSetting extends Setting {
    private boolean enabled;

    public BooleanSetting(String name, boolean defaultValue) {
        super(name);
        this.enabled = defaultValue;
    }

    public BooleanSetting(String name) {
        super(name);
        this.enabled = false;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}