package neptune.module;

import neptune.Neptune;
import neptune.utils.MinecraftInterface;
import neptune.module.settings.Setting;
import net.minecraft.network.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public abstract class Mod implements MinecraftInterface {

    private String name;
    private String displayName;
    private String description;
    private Category Category;
    private int key;
    private boolean enabled;

    private List<Setting> settings = new ArrayList<Setting>();

    public Mod(String name, String description, Category category) {
        this.name = name;
        this.displayName = name;
        this.description = description;
        this.Category = category;
    }

    public boolean nullCheck() {
        return mc.world == null || mc.player == null;
    }
    public List<Setting> getSettings() {
        return settings;
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
            Neptune.getInstance().getEventManager().subscribe(this);
            onEnable();
        } else {
            Neptune.getInstance().getEventManager().unsubscribe(this);
            onDisable();
        }
    }

    public abstract void onEnable();
    public abstract void onDisable();

    public Mod.Category getCategory() {
        return Category;
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
            Neptune.getInstance().getEventManager().subscribe(this);
            onEnable();
        } else {
            Neptune.getInstance().getEventManager().unsubscribe(this);
            onDisable();
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public static void send(Packet<?> packetIn) {
        mc.getNetworkHandler().getConnection().send(packetIn);
    }

    public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        RENDER("Render");

        public String name;

        private Category(String name) {
            this.name = name;
        }
    }
}
