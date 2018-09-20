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

    private static final Pattern PATTERN = Pattern.compile("^I(\\sa|')?m\\s(?<target>.+)$");

    @Value("${twitch.bot.name}")
    private String botName;

    @Override
    public String handle(User user, String message) throws Exception {
        Matcher matcher = PATTERN.matcher(message);
        if (matcher.matches()) {
            String target = matcher.group("target");
            return String.format("Hi %s! I'm klayb0t!", target);
        }
        return null;
    }
}
