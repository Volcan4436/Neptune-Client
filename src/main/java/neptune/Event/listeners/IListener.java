package neptune.Event.listeners;

//Do Not touch this or anything in the Events folder potatoman
public interface IListener {
    void call(Object event);

    Class<?> getTarget();

    int getPriority();

    boolean isStatic();
}

