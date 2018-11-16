package in.dragonbra.klayb0t.command;

import in.dragonbra.klayb0t.repository.JackboxGameRepository;
import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author lngtr
 * @since 2018-08-15
 */
@Component
public class RandomJackboxCommand extends Command {

    private final JackboxGameRepository jackboxGameRepository;

    private final List<String> messages;

    private final Map<String, String> games;

    private final Random random = new Random();

    @Autowired
    public RandomJackboxCommand(JackboxGameRepository jackboxGameRepository,
                                @Qualifier("randomjack_messages") List<String> messages,
                                @Qualifier("randomjack_games") Map<String, String> games) {
        super("randomjack");
        this.jackboxGameRepository = jackboxGameRepository;
        this.messages = messages;
        this.games = new HashMap<String, String>(games);

        this.games.remove("ydkj2015");
        this.games.remove("fakinit");
    }

    @Override
    public String handle(User user, String message, String[] args) {

        List<String> playedGames = this.jackboxGameRepository.getRecentlyPlayed();

        List<String> keys = new ArrayList<>(games.keySet());
        keys.removeAll(playedGames);

        String player = user.getNick();
        String game = games.get(keys.get(random.nextInt(keys.size())));
        String randomMessage = messages.get(random.nextInt(messages.size()));

        randomMessage = randomMessage.replace("%PLAYER%", player).replace("%GAME%", game);

        return randomMessage;
    }
}
