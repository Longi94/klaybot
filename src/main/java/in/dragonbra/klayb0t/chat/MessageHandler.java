package in.dragonbra.klayb0t.chat;

import org.pircbotx.User;

public abstract class MessageHandler {
    public abstract String handle(User user, String message) throws Exception;
}
