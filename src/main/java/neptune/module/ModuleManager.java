package neptune.module;

import neptune.module.api.Category;
import neptune.module.api.Mod;
import neptune.module.impl.client.HUD;
import neptune.module.impl.client.Notifications;
import neptune.module.impl.combat.Critical;
import neptune.module.impl.combat.TriggerBot;
import neptune.module.impl.combat.Wtap;
import neptune.module.impl.exploit.BoatModifier;
import neptune.module.impl.movement.*;
import neptune.module.impl.render.FullBright;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Mod> modules = new ArrayList<>();

    public ModuleManager() {
        // Combat
        modules.add(new Wtap());
        modules.add(new Critical());
        modules.add(new TriggerBot());

        // Movement
        modules.add(new AutoWalk());
        modules.add(new ElytraFly());
        modules.add(new Speed());
        modules.add(new Sprint());
        modules.add(new Velocity());
        modules.add(new JetPack());

        // Player

        // Exploit
        modules.add(new BoatModifier());

        // Render
        modules.add(new FullBright());
        modules.add(new HUD());
        modules.add(new Notifications());

        // Misc
    }

    public List<Mod> getModules() {
        return modules;
    }

    // Heedi u should probably use streams instead of just a for loop
    public List<Mod> getEnabledModules() {
        List<Mod> enabled = new ArrayList<>();

        for (Mod module : modules) {
            if (module.isEnabled())
                enabled.add(module);
        }

        return enabled;
    }

    public Mod getModuleByName(String name) {
        for (Mod module : modules) {
            if (module.getName().equalsIgnoreCase(name))
                return module;
        }

        return null;
    }

    public List<Mod> getModulesInCategory(Category category) {
        List<Mod> categoryModules = new ArrayList<>();

        for (Mod module : modules) {
            if (module.getCategory() == category)
                categoryModules.add(module);
        }

        return categoryModules;
    }
}
