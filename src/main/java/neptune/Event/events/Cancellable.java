package neptune.Event.events;


import neptune.Event.ICancellable;

//Do Not touch this or anything in the Events folder potatoman
public class Cancellable implements ICancellable {
    private boolean cancelled = false;

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}