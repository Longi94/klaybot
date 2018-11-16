package in.dragonbra.klayb0t.command;

import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SayCommand extends Command {

    @Value("${twitch.owner}")
    private String twitchOwner;

    public SayCommand() {
        super("say");
    }

    @Override
    public String handle(User user, String message, String[] args) {
        if (!user.getLogin().equalsIgnoreCase(twitchOwner)) {
            return null;
        }

        return message.substring(5, message.length());
    }
}
