package me.neptune.managers.impl.client;

import me.neptune.Neptune;
import me.neptune.api.module.Module;
import me.neptune.events.client.ModuleEvent;
import me.neptune.managers.util.IterableRegistry;
import me.neptune.modules.client.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager implements IterableRegistry<Module>
{
    private final List<Module> registeredModules = new ArrayList<>();

    public void init()
    {
        /* ----------- CLIENT ----------- */
        register(new HUD());
        register(new Globals());
        register(new ClickGui());

        /* ----------- COMBAT ----------- */


        /* ------------ MISC ------------ */


        /* ---------- MOVEMENT ---------- */


        /* ----------- RENDER ----------- */


        /* ------------ WORLD ----------- */

    }

    @Override
    public void register(Module object) {
        Neptune.EVENT_BUS.register(object);
        registeredModules.add(object);
        Neptune.EVENT_BUS.post(new ModuleEvent.Load(object));
    }

    @Override
    public void unregister(Module object) {
        registeredModules.remove(object);
    }

    @Override
    public List<Module> getRegistered() {
        return registeredModules;
    }
}
