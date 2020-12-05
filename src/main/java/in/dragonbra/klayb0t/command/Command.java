package in.dragonbra.klayb0t.command;

import in.dragonbra.klayb0t.bot.TwitchBot;
import in.dragonbra.klayb0t.chat.BaseHandler;
import org.pircbotx.User;

import java.util.*;

public abstract class Command extends BaseHandler {

    private final Set<String> commandAliases = new HashSet<>();

    private String description;

    private final List<CommandArgument> arguments = new ArrayList<>();

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
        this.commandAliases.add(commandText);
        this.description = description;
    }

    public Set<String> getCommandAliases() {
        return commandAliases;
    }

    public void addCommandAliases(String... aliases) {
        this.commandAliases.addAll(Arrays.asList(aliases.clone()));
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

    public void addArgument(CommandArgument argument) {
        arguments.add(argument);
    }

    public abstract String handle(User user, String message, String[] args);

    public void setBot(TwitchBot bot) {
        this.bot = bot;
    }
}
