package neptune.module;


import neptune.module.client.HUD;
import neptune.module.client.Notifications;
import neptune.module.combat.Wtap;
import neptune.module.movement.*;
import neptune.module.render.FullBright;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static final ModuleManager INSTANCE = new ModuleManager();
    private List<Mod> modules = new ArrayList<>();

    public ModuleManager() {

        // Client
        modules.add(new HUD());
        modules.add(new Notifications());

        // Combat
        modules.add(new Wtap());

        // Exploit

        // Misc

        // Movement
        modules.add(new AutoWalk());
        modules.add(new Speed());
        modules.add(new Sprint());
        modules.add(new Velocity());

        // Render
        modules.add(new FullBright());
    }

    public static double getValue() {
        return getValue();
    }

    public List<Mod> getModules() {
        return modules;
    }

    // Heedi u should probably use streams instead of just a for loop
    public List<Mod> getEnabledModules() {
        List<Mod> enabled = new ArrayList<>();
        for (Mod module : modules) {
            if (module.isEnabled()) enabled.add(module);
        }
        return enabled;
    }

    public Mod getModuleByName(String name) {
        for (Mod module : modules) {
            if (module.getName().equalsIgnoreCase(name)) return module;
        }
        return null;
    }

    public List<Mod> getModulesInCategory(Mod.Category category) {
        List<Mod> categoryModules = new ArrayList<>();
        for (Mod mod : modules) {
            if (mod.getCategory() == category) {
                categoryModules.add(mod);
            }
        }
        return categoryModules;
    }
}
