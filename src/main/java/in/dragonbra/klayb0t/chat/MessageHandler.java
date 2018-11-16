package in.dragonbra.klayb0t.chat;

import org.pircbotx.User;

public abstract class MessageHandler extends BaseHandler {

    public MessageHandler() {
        super();
    }

    public MessageHandler(long coolDown) {
        super(coolDown);
    }

    public abstract String handle(User user, String message) throws Exception;
}
