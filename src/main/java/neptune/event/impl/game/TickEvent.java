package neptune.event.impl.game;

import neptune.event.api.Event;
import neptune.event.api.EventState;

public class TickEvent extends Event {
    private EventState state;

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }
}