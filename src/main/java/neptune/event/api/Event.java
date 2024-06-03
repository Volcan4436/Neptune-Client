package neptune.event.api;

import neptune.utils.MinecraftInterface;

public class Event implements MinecraftInterface {
    private boolean cancelled;

    public <T extends Event> T call() {
        if (mc.player != null || mc.world != null)
            EventAPI.call(this);
        return (T) this;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        this.cancelled = true;
    }
}