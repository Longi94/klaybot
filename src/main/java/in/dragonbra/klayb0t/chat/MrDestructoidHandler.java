package in.dragonbra.klayb0t.chat;

import org.pircbotx.User;

/**
 * @author lngtr
 * @since 2018-09-20
 */
public class MrDestructoidHandler extends MessageHandler {

    private static final long MIN_DELAY = 30000L;

    private long lastRobotHandle = 0L;

    @Override
    public String handle(User user, String message) throws Exception {
        long currentTime = System.currentTimeMillis();
        if (lastRobotHandle + MIN_DELAY < currentTime && message.contains("MrDestructoid")) {
            lastRobotHandle = currentTime;
            return "MrDestructoid";
        }
        return null;
    }
}
