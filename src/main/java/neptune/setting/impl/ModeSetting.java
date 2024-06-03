package neptune.setting.impl;

import neptune.setting.Setting;

public class ModeSetting extends Setting {
    private final String[] modes;
    private int currentIndex = 0;

    public ModeSetting(String name, String... modes) {
        super(name);
        this.modes = modes;
    }

    public String[] getModes() {
        return modes;
    }

    public String getMode() {
        return modes[currentIndex];
    }

    public void increment() {
        currentIndex = ((currentIndex + 1) % modes.length);
    }

    public void decrement() {
        currentIndex = ((currentIndex - 1 + modes.length) % modes.length);
    }

    public boolean is(String mode){
        return modes[currentIndex]
                .equalsIgnoreCase(mode);
    }
}