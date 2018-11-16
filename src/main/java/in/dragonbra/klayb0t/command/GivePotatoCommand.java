package in.dragonbra.klayb0t.command;

import org.pircbotx.User;

/**
 * @author lngtr
 * @since 2018-09-20
 */
public class GivePotatoCommand extends Command {
    public GivePotatoCommand() {
        super("givepotato");
    }

    @Override
    public String handle(User user, String message, String[] args) {
        String target = args.length > 0 ? args[0] : "";
        return "/me \uD83E\uDD54 " + target;
    }
}
