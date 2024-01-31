package neptune.module;




import neptune.features.client.HUD;
import neptune.features.client.Notifications;
import neptune.features.combat.KeyPearlPhase;
import neptune.features.exploit.WorldBorderCrash;
import neptune.features.misc.AutoReconnect;
import neptune.features.misc.NoRotate;
import neptune.features.movement.Flight;
import neptune.features.movement.Sprint;
import neptune.features.movement.Velocity;
import neptune.features.player.AutoExp;
import neptune.features.render.HandModifier;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private List<Mod> modules = new ArrayList<>();

    public ModuleManager() {
        modules.add(new Sprint());
        modules.add(new Flight());
        modules.add(new Velocity());
        modules.add(new HUD());
        modules.add(new KeyPearlPhase());
        modules.add(new WorldBorderCrash());
        modules.add(new AutoReconnect());
        modules.add(new HandModifier());
        modules.add(new AutoExp());
        modules.add(new NoRotate());
        modules.add(new Notifications());
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
