package neptune.module;

import neptune.Neptune;
import neptune.module.api.Category;
import neptune.module.api.Module;
import neptune.utils.LoggerUtils;
import org.reflections.Reflections;

import java.util.*;

public class ModuleManager {
    private final Map<Class<? extends Module>, Module> modules = new HashMap<>();

    public ModuleManager() {
        Reflections reflections = new Reflections("neptune.module");
        Set<Class<? extends Module>> module_classes = reflections.getSubTypesOf(Module.class);

        for (Class<? extends Module> module : module_classes) {
            try {
                addModule(module.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                LoggerUtils.LOGGER.error("Failed to instantiate module: {}", module.getName(), e);
            }
        }
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
                .orElse(null);
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