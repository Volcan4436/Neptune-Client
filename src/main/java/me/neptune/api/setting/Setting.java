package me.neptune.api.setting;

/**
 * Generic superclass for Settings.
 * @param <T> the value type of the Setting
 */
public abstract class Setting<T>
{
    private final String name;
    private final T initial;
    private T value;

    private String description;

    public Setting(String name, T value) {
        this.name = name;
        this.initial = this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getInitial() {
        return initial;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void registerDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
