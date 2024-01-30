package neptune.Event;

//Do Not touch this or anything in the Events folder potatoman
public interface ICancellable {
    void setCancelled(boolean cancelled);

    default void cancel() {
        setCancelled(true);
    }

    boolean isCancelled();
}

