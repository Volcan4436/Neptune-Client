package neptune.event;

import meteordevelopment.orbit.ICancellable;

public abstract class Event implements ICancellable {
    private boolean isCancelled;

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}
