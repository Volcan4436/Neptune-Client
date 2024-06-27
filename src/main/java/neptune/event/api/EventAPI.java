package neptune.event.api;

import io.github.nevalackin.radbus.PubSub;
import neptune.utils.LoggerUtils;

public class EventAPI {
    public static final PubSub<Event> EVENT_BUS = PubSub.newInstance(LoggerUtils.LOGGER::error);

    public static void register(Object object) {
        EVENT_BUS.subscribe(object);
    }

    public static void unregister(Object object) {
        EVENT_BUS.unsubscribe(object);
    }

    public static void call(Event event) {
        EVENT_BUS.publish(event);
    }

    public static void cleanup() {
        EVENT_BUS.clear();
    }
}