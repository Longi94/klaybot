package in.dragonbra.klayb0t.chat;

import in.dragonbra.klayb0t.service.JackboxService;
import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lngtr
 * @since 2018-08-25
 */
@Component
public class JackboxCodeHandler extends MessageHandler {

    private static final Pattern CODE_PATTERN = Pattern.compile("(^|\\s)(?<code>[A-Z]{4})($|\\s)");

    @Value("${twitch.bot.channel}")
    private String twitchBotChannel;

    private final JackboxService jackboxService;

    @Autowired
    public JackboxCodeHandler(JackboxService jackboxService) {
        this.jackboxService = jackboxService;
    }

    @Override
    public String handle(User user, String message) {
        if (!twitchBotChannel.equalsIgnoreCase(user.getLogin())) {
            return null;
        }

        Matcher matcher = CODE_PATTERN.matcher(message);

        while (matcher.find()) {
            String code = matcher.group("code");
            jackboxService.handle(code);
        }

        return null;
    }
}
