package in.dragonbra.klayb0t.command;

import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * @author lngtr
 * @since 2018-08-15
 */
@Component
public class RandomJackboxCommand extends Command {

    private final List<String> messages;

    private final List<String> games;

    private final Random random = new Random();

    @Autowired
    public RandomJackboxCommand(@Qualifier("randomjack_messages") List<String> messages,
                                @Qualifier("randomjack_games") List<String> games) {
        super("randomjack", "");
        this.messages = messages;
        this.games = games;
    }

    @Override
    public String handle(User user, String message, String[] args) {

        String player = user.getNick();
        String game = games.get(random.nextInt(games.size()));
        String randomMessage = messages.get(random.nextInt(messages.size()));

        randomMessage = randomMessage.replace("%PLAYER%", player).replace("%GAME%", game);

        return randomMessage;
    }
}
