package in.dragonbra.klayb0t.chat;

import org.pircbotx.User;

/**
 * @author lngtr
 * @since 2018-09-20
 */
public class MrDestructoidHandler extends MessageHandler {

    private static final long MIN_DELAY = 30000L;

    public MrDestructoidHandler() {
        super(MIN_DELAY);
    }

    @Override
    public String handle(User user, String message) {
        if (message.contains("MrDestructoid")) {
            return "MrDestructoid";
        }
        return null;
    }
}
