package neptune.module.api;

import neptune.utils.ChatUtils;

public enum Category {
    COMBAT, MOVEMENT, PLAYER, EXPLOIT, VISUALS, MISC;

    private final String name;

    Category() {
        this.name = ChatUtils.title(this.name());
    }

    public String getName() {
        return name;
    }
}