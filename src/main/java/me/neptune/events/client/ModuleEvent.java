package me.neptune.events.client;

import me.neptune.api.event.Event;
import me.neptune.api.module.Module;

public class ModuleEvent extends Event
{
    private final Module module;
    public ModuleEvent(Module module) {
        this.module = module;
    }

    public Module getModule(){
        return module;
    }

    public static class Load extends ModuleEvent
    {
        public Load(Module module) {
            super(module);
        }
    }

    public static class Enable extends ModuleEvent
    {
        public Enable(Module module) {
            super(module);
        }
    }

    public static class Disable extends ModuleEvent
    {
        public Disable(Module module) {
            super(module);
        }
    }
}
