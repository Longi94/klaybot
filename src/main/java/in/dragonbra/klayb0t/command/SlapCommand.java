package in.dragonbra.klayb0t.command;

import com.google.common.collect.ImmutableList;
import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class SlapCommand extends Command {

    private final Random rand = new Random();

    private static final List<String> THINGS = ImmutableList.of(
            "a cactus", "a trout", "a gun", "a book", "a pie", "their face", "their nipple", "spaghetti",
            "big meaty claws", "adr525"
    );

    @Value("${twitch.bot.name}")
    private String botName;

    public SlapCommand() {
        super("slap");
    }

    @Override
    public String handle(User user, String message, String[] args) {
        List<String> users = bot.getUsers();

        users.remove(botName);

        String target = users.get(rand.nextInt(users.size()));
        String item = THINGS.get(rand.nextInt(THINGS.size()));

        if (target.equals(user.getNick())) {
            return String.format("%s slapped themselves with %s, stop hitting yourself!", target, item);
        } else {
            return String.format("%s slapped %s with %s!", user.getNick(), target, item);
        }
    }
}
