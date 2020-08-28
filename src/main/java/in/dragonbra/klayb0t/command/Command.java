package in.dragonbra.klayb0t.command;

import in.dragonbra.klayb0t.bot.TwitchBot;
import in.dragonbra.klayb0t.chat.BaseHandler;
import org.pircbotx.User;

import java.util.ArrayList;
import java.util.List;

public abstract class Command extends BaseHandler {

    private String commandText;

    private String description;

    private List<CommandArgument> arguments = new ArrayList<>();

    protected TwitchBot bot;

    public Command(String commandText) {
        this(commandText, "");
    }

    public Command(String commandText, String description) {
        this(commandText, description, 0L);
    }

    public Command(String commandText, long coolDown) {
        this(commandText, "", coolDown);
    }

    public Command(String commandText, String description, long coolDown) {
        super(coolDown);
        this.commandText = commandText;
        this.description = description;
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CommandArgument> getArguments() {
        return arguments;
    }

    public void setArguments(List<CommandArgument> arguments) {
        this.arguments = arguments;
    }

    public String getHelpText() {
        StringBuilder builder = new StringBuilder(commandText);

        for (CommandArgument argument : arguments) {
            builder.append(" ").append(argument);
        }

        builder.append(" - ").append(description);

        return builder.toString();
    }

    public void addArgument(CommandArgument argument) {
        arguments.add(argument);
    }

    public abstract String handle(User user, String message, String[] args);

    public void setBot(TwitchBot bot) {
        this.bot = bot;
    }
}
