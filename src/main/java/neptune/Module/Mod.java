package neptune.Module;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.Packet;
import neptune.Module.settings.Setting;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Mod {

    private String name;
    private String displayName;
    private String description;
    private Category Category;
    private int key;
    private boolean enabled;

    private List<Setting> settings = new ArrayList<Setting>();

    protected static MinecraftClient mc = MinecraftClient.getInstance();

    public Mod(String name, String description, Category category) {
        this.name = name;
        this.displayName = name;
        this.description = description;
        this.Category = category;
    }

    public boolean isNull() {
        return Objects.isNull(mc.world) || Objects.isNull(mc.player) || Objects.isNull(mc.player);
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

        if (enabled) onEnable();
        else onDisable();
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onWorldRender(MatrixStack matrices) {
    }


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

    public void toggle(boolean enabled) {
        this.enabled = enabled;

        if (enabled) onEnable();
        else onDisable();
    }

    public String getDisplayName() {
        return displayName;
    }

    public String setDisplayname(String displayName) {
        this.displayName = displayName;
        return displayName;
    }

    public boolean onTick() {

        return true;
    }

    public static void send(final Packet<?> packetIn) {
        if (packetIn == null) {
            return;
        }
        Objects.requireNonNull(mc.getNetworkHandler()).getConnection().send(packetIn);
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
