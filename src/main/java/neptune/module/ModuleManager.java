package neptune.module;

import neptune.Neptune;
import neptune.module.api.Category;
import neptune.module.api.Module;
import neptune.module.impl.combat.*;
import neptune.module.impl.misc.Commands;
import neptune.module.impl.movement.*;
import neptune.module.impl.exploit.*;
import neptune.module.impl.visuals.*;

import java.util.*;

public class ModuleManager {
    private final Map<Class<? extends Module>, Module> modules = new HashMap<>();

    public ModuleManager() {
        // Combat
        addModule(new WTap());
        addModule(new Critical());
        addModule(new TriggerBot());

        // Movement
        addModule(new AutoWalk());
        addModule(new ElytraFly());
        addModule(new Speed());
        addModule(new Sprint());
        addModule(new Velocity());
        addModule(new JetPack());

        // Player

        // Exploit
        addModule(new BoatModifier());

        // Visual
        addModule(new FullBright());
        addModule(new HUD());
        addModule(new Notifications());
        addModule(new ClickGUI());

        // Misc
        addModule(new Commands());
    }

    private void addModule(Module module) {
        modules.put(module.getClass(), module);
    }

    public Module getModule(Class<? extends Module> clazz) {
        return modules.get(clazz);
    }

    public Module getModule(String name) {
        return modules.values().stream()
                .filter(module -> module.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Module not found"));
    }

    public Collection<Module> getModules() {
        return modules.values();
    }

    public List<Module> getEnabledModules() {
        return modules.values().stream()
                .filter(Module::isToggled)
                .toList();
    }

    public List<Module> getModulesInCategory(Category category) {
        return modules.values().stream()
                .filter(module -> module.getCategory() == category)
                .toList();
    }

    public static ModuleManager getInstance() {
        return Neptune.INSTANCE.getModuleManager();
    }
}