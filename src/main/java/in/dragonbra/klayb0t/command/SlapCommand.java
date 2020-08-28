package in.dragonbra.klayb0t.command;

import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class SlapCommand extends Command {

    private final Random rand = new Random();

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

        if (target.equals(user.getNick())) {
            return String.format("%s slapped themselves, stop hitting yourself!", target);
        } else {
            return String.format("%s slapped %s!", user.getNick(), target);
        }
    }
}
