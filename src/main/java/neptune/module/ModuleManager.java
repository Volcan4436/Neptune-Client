package neptune.module;


import neptune.module.client.HUD;
import neptune.module.client.Notifications;
import neptune.module.combat.KeyPearlPhase;
import neptune.module.exploit.NoMiningTrace;
import neptune.module.exploit.WorldBorderCrash;
import neptune.module.misc.AutoReconnect;
import neptune.module.misc.NoRotate;
import neptune.module.movement.*;
import neptune.module.player.AutoExp;
import neptune.module.render.FullBright;
import neptune.module.render.HandModifier;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    private List<Mod> modules = new ArrayList<>();

    public ModuleManager() {
        // Client
        modules.add(new HUD());
        modules.add(new Notifications());

        // Combat
        modules.add(new KeyPearlPhase());

        // Exploit
        modules.add(new NoMiningTrace());
        modules.add(new WorldBorderCrash());

        // Misc
        modules.add(new AutoReconnect());
        modules.add(new NoRotate());

        // Movement
        modules.add(new AutoWalk());
        modules.add(new Flight());
        modules.add(new NoSlow());
        modules.add(new ReverseStep());
        modules.add(new Speed());
        modules.add(new Sprint());
        modules.add(new Step());
        modules.add(new Velocity());

        // Player
        modules.add(new AutoExp());

        // Render
        modules.add(new HandModifier());
        modules.add(new FullBright());
    }
    public static double getValue() {
        return getValue();
    }

    public static NoSlow getValue(Class<NoSlow> noSlowClass) {
        return null;
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
