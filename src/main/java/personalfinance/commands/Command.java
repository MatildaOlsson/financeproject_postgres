package personalfinance.commands;

public abstract class Command {
    protected final String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void execute();
}


