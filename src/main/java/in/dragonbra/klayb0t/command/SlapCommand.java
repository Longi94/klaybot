package in.dragonbra.klayb0t.command;

import com.google.common.collect.ImmutableList;
import in.dragonbra.klayb0t.service.TwitchService;
import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
public class SlapCommand extends Command {

    private final Random rand = new Random();

    private static final String RANDOM_VIEWER = "random_viewer";
    private static final List<String> THINGS = ImmutableList.of(
            "a cactus", "a trout", "a gun", "a book", "a pie", "their face", "their nipple", "spaghetti",
            "big meaty claws", RANDOM_VIEWER
    );

    private final TwitchService twitchService;
    private final Set<String> excludeList;

    public SlapCommand(TwitchService twitchService, @Qualifier("slap_exclude_list") Set<String> excludeList) {
        super("slap");
        this.twitchService = twitchService;
        this.excludeList = excludeList;
    }

    @Override
    public String handle(User user, String message, String[] args) {
        List<String> users;

        try {
            users = twitchService.getViewerNames();
        } catch (IOException e) {
            return null;
        }

        users.removeAll(excludeList);

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
