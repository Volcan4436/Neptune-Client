package neptune.module.api;

public enum Category {
    COMBAT, MOVEMENT, PLAYER, EXPLOIT, RENDER, MISC;

    private final String name;

    Category() {
        this.name = this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }

    public String getName() {
        return name;
    }
}
