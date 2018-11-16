package in.dragonbra.klayb0t.command;

import org.pircbotx.User;

public class PlayCommand extends Command {

    private static final long COMMAND_DELAY = 180000L;

    public PlayCommand() {
        super("play", COMMAND_DELAY);
    }

    @Override
    public String handle(User user, String message, String[] args) {
        return "!play";
    }
}
