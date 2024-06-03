package neptune.event.impl.game;

import neptune.event.api.Event;

public class ChatEvent extends Event {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}