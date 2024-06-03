package neptune.event.impl.input;

import neptune.event.api.Event;

public class KeyboardEvent extends Event {
    private int key;
    private int action;

    public void setKey(int key) {
        this.key = key;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getKey() {
        return key;
    }

    public int getAction() {
        return action;
    }
}