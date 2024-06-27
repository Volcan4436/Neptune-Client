package neptune.module.api;

import neptune.event.api.EventAPI;
import neptune.setting.Setting;
import neptune.utils.ChatUtils;
import neptune.utils.MinecraftInterface;
import neptune.utils.MiscUtils;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class Module implements MinecraftInterface {
    private final ModuleInfo info = this.getClass().getAnnotation(ModuleInfo.class);
    private final String name = info.name().isEmpty() ?
            this.getClass().getSimpleName()
            : info.name();
    private final String description = info.description();
    private final Category category = MiscUtils.getCategoryByPackage(this.getClass());

    private int key = info.key();
    private boolean toggled;

    private final Flags[] flags = info.flags();
    private final List<Setting> settings = new ArrayList<>();

    public void onEnable() {}
    public void onDisable() {}

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        if (!name.equals("ClickGUI"))
            ChatUtils.messageBranding(Formatting.GRAY + "Toggled " + Formatting.WHITE + name + " " + (toggled ?
                    Formatting.GREEN + "on"
                    : Formatting.RED + "off"));

        if (toggled) {
            EventAPI.register(this);
            onEnable();
        } else {
            EventAPI.unregister(this);
            onDisable();
        }
    }

    public void toggle() {
        setToggled(!toggled);
    }

    public void addSettings(Setting... settings) {
        this.settings.addAll(List.of(settings));
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isToggled() {
        return toggled;
    }

    public Flags[] getFlags() {
        return flags;
    }

    public List<Setting> getSettings() {
        return settings;
    }
}