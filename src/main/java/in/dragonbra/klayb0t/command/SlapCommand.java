package in.dragonbra.klayb0t.command;

import com.google.common.collect.ImmutableList;
import in.dragonbra.klayb0t.entity.SlapUser;
import in.dragonbra.klayb0t.repository.SlapUserRepository;
import in.dragonbra.klayb0t.service.TwitchService;
import org.pircbotx.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SlapCommand extends Command {

    private final Random rand = new Random();

    private static final String RANDOM_VIEWER = "random_viewer";
    private static final List<String> THINGS = ImmutableList.of(
            "a cactus", "a trout", "a gun", "a book", "a pie", "their face", "their nipple", "spaghetti",
            "big meaty claws", RANDOM_VIEWER
    );

    private final SlapUserRepository slapUserRepository;
    private final TwitchService twitchService;

    public SlapCommand(SlapUserRepository slapUserRepository, TwitchService twitchService) {
        super("slap");
        this.slapUserRepository = slapUserRepository;
        this.twitchService = twitchService;
    }

    @Override
    public String handle(User user, String message, String[] args) {
        Set<String> users;

        try {
            users = twitchService.getViewerNames();
        } catch (IOException e) {
            return null;
        }

        Set<String> includeList =
                slapUserRepository.findAllByExcludeFalse().stream().map(SlapUser::getName).collect(Collectors.toSet());
        users.retainAll(includeList);

        List<String> validUsers = new ArrayList<>(users);

        String target = validUsers.get(rand.nextInt(validUsers.size()));
        String item = THINGS.get(rand.nextInt(THINGS.size()));

        if (item.equals(RANDOM_VIEWER)) {
            item = validUsers.get(rand.nextInt(validUsers.size()));
        }

        if (target.equals(user.getNick())) {
            return String.format("%s slapped themselves with %s, stop hitting yourself!", target, item);
        } else {
            return String.format("%s slapped %s with %s!", user.getNick(), target, item);
        }
    }
}
