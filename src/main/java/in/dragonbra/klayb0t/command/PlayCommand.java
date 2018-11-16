package in.dragonbra.klayb0t.command;

import org.pircbotx.User;

public class PlayCommand extends Command {

    private static final long COMMAND_DELAY = 180000L;

    private long lastCommand = 0L;

    public PlayCommand() {
        super("play", "");
    }

    @Override
    public String handle(User user, String message, String[] args) {
        long currentTimestamp = System.currentTimeMillis();
        if (lastCommand < currentTimestamp - COMMAND_DELAY) {
            lastCommand = currentTimestamp;
            return "!play";
        }
        return null;
    }
}
