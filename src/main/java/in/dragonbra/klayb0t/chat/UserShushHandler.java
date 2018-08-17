package in.dragonbra.klayb0t.chat;

import org.pircbotx.User;

import java.util.Random;

/**
 * @author lngtr
 * @since 2018-08-17
 */
public class UserShushHandler extends MessageHandler {

    private final String user;

    private final double chance;

    private final Random random = new Random();

    public UserShushHandler(String user, double chance) {
        this.user = user;
        this.chance = chance;
    }

    @Override
    public String handle(User user, String message) {
        return user.getLogin().equalsIgnoreCase(this.user) && random.nextDouble() <= chance ?
                "Shush " + user.getNick() : null;
    }
}
