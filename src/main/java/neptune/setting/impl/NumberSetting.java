package neptune.setting.impl;

import neptune.setting.Setting;
import net.minecraft.util.math.MathHelper;

/* TODO:
*   Add Units
*   Multi Number Type Support*/
public class NumberSetting extends Setting {
    private final double minimum, maximum, increment;
    private double value;

    public NumberSetting(String name, double defaultValue, double minimum, double maximum, double increment) {
        super(name);
        this.minimum = minimum;
        this.maximum = maximum;
        this.value = defaultValue;
        this.increment = increment;
    }

    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public double getIncrement() {
        return increment;
    }

    public double getValue() {
        return value;
    }

    public float getValueFloat() {
        return (float) value;
    }

    public int getValueInt() {
        return (int) value;
    }

    public void setValue(double value) {
        value = MathHelper.clamp(value, minimum, maximum);
        value = Math.round(value * (1.0 / increment)) / (1.0 / increment);
        this.value = value;
    }
}