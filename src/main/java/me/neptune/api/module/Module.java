package me.neptune.api.module;

import com.google.common.eventbus.Subscribe;
import me.neptune.Neptune;
import me.neptune.api.InstanceHolder;
import me.neptune.api.setting.Setting;
import me.neptune.events.client.ModuleEvent;
import me.neptune.util.minecraft.ChatUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class Module implements InstanceHolder
{
    private final List<Setting<?>> settings = new ArrayList<>();
    private final String name;
    private final Category category;
    private String description;
    private boolean enabled = false;

    public Module(String nameIn, Category category)
    {
        this.name = nameIn;
        this.category = category;
        this.description = "A module. (" + category.getString() + ")";
    }

    @Subscribe
    public void onEnabledChange(ModuleEvent event)
    {
        if(event.getModule().equals(this))
        {
            ChatUtil.sendMessage("ModuleEvent received. Handling onEnableChange! Module: " + getName(), true);

            if (event instanceof ModuleEvent.Enable)
                onEnable();
            else
                onDisable();

        }
    }

    /**
     * Gets the Name of the Module.
     * @return name
     */
    public String getName() {
        return name;
    }

    public String getHudData() {
        return null;
    }

    public Category getCategory() {
        return category;
    }

    public List<Setting<?>> getModuleSettings(){
        return settings;
    }

    public void registerDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        if (enabled) {
            Neptune.EVENT_BUS.post(new ModuleEvent.Enable(this));
            //ChatUtil.sendMessage("ModuleEvent.Enable sent to eventbus!", true);
        }
        else {
            Neptune.EVENT_BUS.post(new ModuleEvent.Disable(this));
            //ChatUtil.sendMessage("ModuleEvent.Disable sent to eventbus!", true);
        }

    }

    public void onEnable() {
        Neptune.EVENT_BUS.register(this);
        /* implemented by the module */
    }

    public void onDisable() {
        Neptune.EVENT_BUS.unregister(this);
        /* implemented by the module */
    }

    public void toggle() {
        setEnabled(!isEnabled());
    }

    /**
     * Adds a setting to the Module's setting list and registers it to the event bus.
     * @param setting the Setting to add.
     * @return the setting so this method can be used to instantiate them.
     * @param <R> the type of the setting (not needed in the method call)
     * @param <S> the Setting (will be returned)
     */
    public <R, S extends Setting<R>> S add(S setting) {
        Neptune.EVENT_BUS.register(setting);
        settings.add(setting);
        return setting;
    }
}
