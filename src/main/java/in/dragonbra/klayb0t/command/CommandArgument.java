package in.dragonbra.klayb0t.command;

public class CommandArgument {

    private String name;

    private boolean required;

    public CommandArgument(String name, boolean required) {
        this.name = name;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public String toString() {
        if (required) {
            return "[" + name + "]";
        } else {
            return "<" + name + ">";
        }
    }
}
