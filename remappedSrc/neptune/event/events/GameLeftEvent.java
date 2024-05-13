package neptune.event.events;

public class GameLeftEvent {
    private static final GameLeftEvent INSTANCE = new GameLeftEvent();

    public static GameLeftEvent get() {
        return INSTANCE;
    }
}
