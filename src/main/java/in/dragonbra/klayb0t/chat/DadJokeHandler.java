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
    private static final int MAX_LENGTH = 25;
    private static final int MAX_WORD_COUNT = 3;

    private static final Pattern PATTERN = Pattern.compile("^(([Ii](\\sa|')?)|a)m\\s(?<target>.+)$");

    @Value("${twitch.bot.name}")
    private String botName;

    public DadJokeHandler() {
        super(MIN_DELAY);
    }

    @Override
    public String handle(User user, String message) {
        Matcher matcher = PATTERN.matcher(message);

        if (!matcher.matches()) {
            return null;
        }

        String target = matcher.group("target");

        if (target.split("\\s+").length > MAX_WORD_COUNT) {
            return null;
        }

        if (target.length() > MAX_LENGTH) {
            return null;
        }

        return String.format("Hi %s! I'm %s!", target, botName);
    }
}
