package in.dragonbra.klayb0t.chat;

import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lngtr
 * @since 2018-09-20
 */
@Component
public class DadJokeHandler extends MessageHandler {

    private static final long MIN_DELAY = 60000L;

    private static final Pattern PATTERN = Pattern.compile("^(([Ii](\\sa|')?)|a)m\\s(?<target>.+)$");

    @Value("${twitch.bot.name}")
    private String botName;

    private long lastTimestamp = 0L;

    @Override
    public String handle(User user, String message) {
        long currentTimestamp = System.currentTimeMillis();

        if (lastTimestamp > currentTimestamp - MIN_DELAY) {
            return null;
        }

        lastTimestamp = currentTimestamp;

        Matcher matcher = PATTERN.matcher(message);

        if (!matcher.matches()) {
            return null;
        }

        String target = matcher.group("target");

        if (target.length() > 25) {
            return null;
        }

        return String.format("Hi %s! I'm %s!", target, botName);
    }
}
