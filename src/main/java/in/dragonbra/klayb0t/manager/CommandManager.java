package in.dragonbra.klayb0t.manager;

import in.dragonbra.klayb0t.bot.TwitchBot;
import in.dragonbra.klayb0t.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pircbotx.User;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class CommandManager {

    private static final Logger logger = LogManager.getLogger(CommandManager.class);

    @Value("${twitch.bot.prefix}")
    private String commandPrefix;

    private final Map<String, Command> commands = new TreeMap<>();

    public void registerCommand(Command command) {
        if (commands.containsKey(command.getCommandText())) {
            logger.warn("command " + command.getCommandText() + " overridden");
        } else {
            logger.info("registered command " + command.getCommandText());
        }

        commands.put(command.getCommandText(), command);
    }

    public String onMessage(GenericMessageEvent event, long timestamp) {
        User user = event.getUser();
        String message = event.getMessage();

        if (!message.trim().startsWith(commandPrefix)) {
            return null;
        }

        String[] split = message.trim().substring(commandPrefix.length()).replaceAll("\\s\\s+", " ").split(" ");

        if (split.length >= 1) {
            String command = split[0];
            String[] args = split.length >= 2 ? Arrays.copyOfRange(split, 1, split.length) : new String[0];

            Command handler = commands.get(command);
            if (handler != null && handler.canExecute(timestamp)) {
                handler.setLastHandle(timestamp);
                return handler.handle(user, message, args);
            } else {
                logger.info("Unknown command: " + command);
            }
        }
        return null;
    }

    public void setBot(TwitchBot bot) {
        for (Command command : commands.values()) {
            command.setBot(bot);
        }
    }
}
