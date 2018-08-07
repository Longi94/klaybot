package in.dragonbra.klayb0t.command;

import org.pircbotx.User;

/**
 * @author lngtr
 * @since 2018-01-27
 */
public class SimpleResponseCommand extends Command {

    private final String response;

    public SimpleResponseCommand(String command, String description, String response) {
        super(command, description);
        this.response = response;
    }

    @Override
    public String handle(User user, String message, String[] args) {
        return response;
    }
}
