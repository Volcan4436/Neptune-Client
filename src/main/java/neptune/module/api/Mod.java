package neptune.module.api;

import neptune.Neptune;
import neptune.setting.Setting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Mod {
    protected static MinecraftClient mc = MinecraftClient.getInstance();
    private String name;
    protected static final Random random = ThreadLocalRandom.current();
    private String displayName;
    private String description;
    private final Category category;
    private int key;
    private boolean enabled;

    private final List<Setting> settings = new ArrayList<>();

    public Mod(String name, String description, Category category) {
        this.name = name;
        this.displayName = name;
        this.description = description;
        this.category = category;
    }

    public boolean nullCheck() {
        return mc.world == null || mc.player == null;
    }

    public List<Setting> getSettings() {
        return settings;
    }

    public void onTick() {
        if (mc.world == null || mc.player == null)
            return;
    }

    public void addSetting(Setting setting) {
        settings.add(setting);
    }

    public void addSettings(Setting... settings) {
        for (Setting setting : settings) addSetting(setting);
    }

    public void toggle() {
        this.enabled = !this.enabled;
        if (enabled) {
            mc.inGameHud.getChatHud().addMessage(Text.of(Formatting.BLUE + "[Neptune] " + Formatting.WHITE + this.name + " " + Formatting.DARK_GREEN + "[+]"));
            Neptune.getInstance().EVENT_BUS.subscribe(this);
            onEnable();
        } else {
            mc.inGameHud.getChatHud().addMessage(Text.of(Formatting.BLUE + "[Neptune] " + Formatting.WHITE + this.name + " " + Formatting.DARK_RED + "[-]"));
            Neptune.getInstance().EVENT_BUS.unsubscribe(this);
            onDisable();
        }
    }

    public void onEnable () {
        if (mc.world == null || mc.player == null)
            return;
    }
    public void onDisable() {
        if (mc.world == null || mc.player == null)
            return;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKey() {
        return key;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            Neptune.getInstance().EVENT_BUS.subscribe(this);
            onEnable();
        } else {
            Neptune.getInstance().EVENT_BUS.unsubscribe(this);
            onDisable();
        }
    }

    public String getDisplayName() {
        return displayName;
    }
}
