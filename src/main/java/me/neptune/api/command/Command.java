package me.neptune.api.command;

public abstract class Command
{
    private final String name;
    private final String[] args;

    public Command(String name, String[] args) {
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public String[] getValidArgs() {
        return args;
    }

    public void run(String[] args) {
        /* implemented by the command */
    }
}
