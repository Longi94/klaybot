package in.dragonbra.klayb0t.command;

import org.pircbotx.User;

/**
 * @author lngtr
 * @since 2018-08-25
 */
public class EatFruitCommand extends Command {

    private int state = 0;

    public EatFruitCommand() {
        super("eatfruit");
    }

    @Override
    public String handle(User user, String message, String[] args) {
        switch (state) {
            case 0:
                state++;
                return "/me burps";
            case 1:
                state++;
                return "/me buuuuurps";
            case 2:
                state = 0;
                return "/me BUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUURPS";
            default:
                state = 1;
                return "/me burps";
        }
    }
}
