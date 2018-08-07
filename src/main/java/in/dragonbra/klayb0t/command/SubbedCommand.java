package in.dragonbra.klayb0t.command;

import org.pircbotx.User;
import org.springframework.stereotype.Component;

/**
 * @author lngtr
 * @since 2018-08-07
 */
@Component
public class SubbedCommand extends Command {
    public SubbedCommand() {
        super("subbed", "check how long have you been subbed");
    }

    @Override
    public String handle(User user, String message, String[] args) {
        return null;
    }
}
