package neptune.setting;

import java.util.function.BooleanSupplier;

public class Setting {
    private final String name;
    private BooleanSupplier visibility;

    public Setting(String name) {
        this.name = name;
    }

    public <T extends Setting> T require(BooleanSupplier visibility) {
        this.visibility = visibility;
        return (T) this;
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visibility == null || visibility.getAsBoolean();
    }
}