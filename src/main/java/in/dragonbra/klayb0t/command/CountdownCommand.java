package in.dragonbra.klayb0t.command;

import in.dragonbra.klayb0t.thread.Delay;
import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CountdownCommand extends Command {

    @Value("${twitch.bot.channel}")
    private String channelOwner;

    public CountdownCommand() {
        super("countdown");
        addCommandAliases("cd", "coutndown");
    }

    @Override
    public String handle(User user, String message, String[] args) {
        if (user.getNick().toLowerCase().equals(channelOwner.toLowerCase())) {
            Delay.run(() -> bot.sendMessage("Go Gettem! HyperCrown"), 6000);
        }
        return null;
    }
}
