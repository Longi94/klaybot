package in.dragonbra.klayb0t.command;

import in.dragonbra.klayb0t.repository.JackboxGameRepository;
import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lngtr
 * @since 2018-09-04
 */
@Component
public class TodayCommand extends Command {

    private final JackboxGameRepository jackboxGameRepository;

    private Map<String, String> gameMapping;

    @Autowired
    public TodayCommand(JackboxGameRepository jackboxGameRepository,
                        @Qualifier("randomjack_games") Map<String, String> gameMapping) {
        super("today");
        this.jackboxGameRepository = jackboxGameRepository;
        this.gameMapping = gameMapping;
    }

    @Override
    public String handle(User user, String message, String[] args) {
        List<String> games = this.jackboxGameRepository.getRecentlyPlayed();

        String playedGames = games.stream().map(tag -> gameMapping.get(tag)).collect(Collectors.joining(", "));

        if (playedGames == null || playedGames.isEmpty()) {
            return "Looks like we haven't played any games today.";
        }

        return "We've played these games today: " + playedGames;
    }
}
