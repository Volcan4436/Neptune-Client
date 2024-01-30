package neptune.Event;


import neptune.Event.listeners.IListener;
import neptune.Event.listeners.LambdaListener;

//Do Not touch this or anything in the Events folder potatoman
public interface IEventBus {
    void registerLambdaFactory(String packagePrefix, LambdaListener.Factory factory);

    <T> T post(T event);

    <T extends ICancellable> T post(T event);

    void subscribe(Object object);

    void subscribe(Class<?> klass);

    void subscribe(IListener listener);

    void unsubscribe(Object object);

    void unsubscribe(Class<?> klass);

    void unsubscribe(IListener listener);
}

