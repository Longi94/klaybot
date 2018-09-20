package in.dragonbra.klayb0t.bot;

import in.dragonbra.klayb0t.chat.DadJokeHandler;
import in.dragonbra.klayb0t.chat.JackboxCodeHandler;
import in.dragonbra.klayb0t.chat.MessageHandler;
import in.dragonbra.klayb0t.chat.MrDestructoidHandler;
import in.dragonbra.klayb0t.manager.CommandManager;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PingEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lngtr
 * @since 2018-01-27
 */
@Component
public class TwitchBot extends ListenerAdapter {

    // 5 mins
    private static final int RECONNECT_DELAY = 300000;

    @Value("${twitch.bot.name}")
    private String twitchBotName;

    @Value("${twitch.bot.oauth}")
    private String twitchBotOAuth;

    @Value("${twitch.bot.channel}")
    private String twitchBotChannel;

    private PircBotX bot;

    private final CommandManager commandManager;

    private final List<MessageHandler> messageHandlers = new LinkedList<>();

    private final JackboxCodeHandler jackboxCodeHandler;

    private final DadJokeHandler dadJokeHandler;

    public TwitchBot(CommandManager commandManager,
                     JackboxCodeHandler jackboxCodeHandler,
                     DadJokeHandler dadJokeHandler) {
        this.commandManager = commandManager;
        this.jackboxCodeHandler = jackboxCodeHandler;
        this.dadJokeHandler = dadJokeHandler;
    }

    @PostConstruct
    private void setup() {
        Configuration config = new Configuration.Builder()
                .addCapHandler(new EnableCapHandler("twitch.tv/tags"))
                .setName(twitchBotName)
                .addServer("irc.chat.twitch.tv", 6667)
                .setServerPassword(twitchBotOAuth)
                .addListener(this)
                .addAutoJoinChannel("#" + twitchBotChannel)
                .setAutoReconnect(true)
                .setAutoReconnectDelay(RECONNECT_DELAY)
                .buildConfiguration();

        bot = new PircBotX(config);

        //addMessageHandler(new UserShushHandler("taterbb8", 0.33));
        addMessageHandler(jackboxCodeHandler);
        addMessageHandler(dadJokeHandler);
        addMessageHandler(new MrDestructoidHandler());
    }

    /**
     * PircBotx will return the exact message sent and not the raw line
     */
    @Override
    public void onGenericMessage(GenericMessageEvent event) throws Exception {
        String response = commandManager.onMessage(event);
        sendMessage(response);

        for (MessageHandler messageHandler : messageHandlers) {
            sendMessage(messageHandler.handle(event.getUser(), event.getMessage()));
        }
    }

    /**
     * We MUST respond to this or else we will get kicked
     */
    @Override
    public void onPing(PingEvent event) {
        bot.sendRaw().rawLineNow(String.format("PONG %s\r\n", event.getPingValue()));
    }

    public void sendMessage(String message) {
        if (message != null) {
            bot.sendIRC().message("#" + twitchBotChannel, message);
        }
    }

    public void sendWhisper(String recipient, String message) {
        if (message != null & recipient != null) {
            bot.sendIRC().message("#" + twitchBotChannel, "/w " + recipient + " " + message);
        }
    }

    public void start() throws IOException, IrcException {
        bot.startBot();
    }

    public void addMessageHandler(MessageHandler messageHandler) {
        messageHandlers.add(messageHandler);
    }
}
