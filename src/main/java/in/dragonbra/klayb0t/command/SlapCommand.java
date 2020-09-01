package in.dragonbra.klayb0t.command;

import com.google.common.collect.ImmutableList;
import in.dragonbra.klayb0t.service.TwitchService;
import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Component
public class SlapCommand extends Command {

    private final Random rand = new Random();

    private static final String RANDOM_VIEWER = "random_viewer";
    private static final List<String> THINGS = ImmutableList.of(
            "a cactus", "a trout", "a gun", "a book", "a pie", "their face", "their nipple", "spaghetti",
            "big meaty claws", RANDOM_VIEWER
    );

    @Value("${twitch.bot.name}")
    private String botName;

    private final TwitchService twitchService;

    public SlapCommand(TwitchService twitchService) {
        super("slap");
        this.twitchService = twitchService;
    }

    @Override
    public String handle(User user, String message, String[] args) {
        List<String> users;

        try {
            users = twitchService.getViewerNames();
        } catch (IOException e) {
            return null;
        }

        users.remove(botName);

        String target = users.get(rand.nextInt(users.size()));
        String item = THINGS.get(rand.nextInt(THINGS.size()));

        if (item.equals(RANDOM_VIEWER)) {
            item = users.get(rand.nextInt(users.size()));
        }

        if (target.equals(user.getNick())) {
            return String.format("%s slapped themselves with %s, stop hitting yourself!", target, item);
        } else {
            return String.format("%s slapped %s with %s!", user.getNick(), target, item);
        }
    }
}
