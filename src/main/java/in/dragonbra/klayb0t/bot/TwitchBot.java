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
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PingEvent;
import org.pircbotx.hooks.events.UnknownEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lngtr
 * @since 2018-01-27
 */
@Component
public class TwitchBot extends ListenerAdapter {

    // 5 mins
    private static final int RECONNECT_DELAY = 300000;

    private static final Pattern IRC_PATTERN = Pattern
            .compile("^(?:@([^\\r\\n ]*) +|())(?::([^\\r\\n ]+) +|())([^\\r\\n ]+)(?: +([^:\\r\\n ]+[^\\r\\n ]*(?: +[^:\\r\\n ]+[^\\r\\n ]*)*)|())?(?: +:([^\\r\\n]*)| +())?[\\r\\n]*$");

    @Value("${twitch.bot.name}")
    private String twitchBotName;

    @Value("${twitch.bot.oauth}")
    private String twitchBotOAuth;

    @Value("${twitch.bot.channel}")
    private String twitchBotChannel;

    @Value("${twitch.owner}")
    private String twitchOwner;

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

    private final Listener ircListener = event -> {
        if (event instanceof UnknownEvent) {
            String line = ((UnknownEvent) event).getLine();
            Matcher matcher = IRC_PATTERN.matcher(line);

            if (!matcher.matches()) {
                return;
            }

            if ("WHISPER".equals(matcher.group(5))) {
                onWhisper(line.substring(1, line.indexOf('!')), matcher.group(8));
            }

        }
    };

    @PostConstruct
    private void setup() {
        Configuration config = new Configuration.Builder()
                .addCapHandler(new EnableCapHandler("twitch.tv/tags"))
                .addCapHandler(new EnableCapHandler("twitch.tv/commands"))
                .addListener(ircListener)
                .setName(twitchBotName)
                .addServer("irc.chat.twitch.tv", 6667)
                .setServerPassword(twitchBotOAuth)
                .addListener(this)
                .addAutoJoinChannel("#" + twitchBotChannel)
                .setAutoReconnect(true)
                .setAutoReconnectDelay(RECONNECT_DELAY)
                .buildConfiguration();

        bot = new PircBotX(config);

        addMessageHandler(jackboxCodeHandler);
        addMessageHandler(dadJokeHandler);
        addMessageHandler(new MrDestructoidHandler());
    }

    /**
     * PircBotx will return the exact message sent and not the raw line
     */
    @Override
    public void onGenericMessage(GenericMessageEvent event) throws Exception {
        long currentTimestamp = System.currentTimeMillis();

        String response = commandManager.onMessage(event, currentTimestamp);
        sendMessage(response);

        for (MessageHandler messageHandler : messageHandlers) {
            if (messageHandler.canExecute(currentTimestamp)) {
                messageHandler.setLastHandle(currentTimestamp);
                sendMessage(messageHandler.handle(event.getUser(), event.getMessage()));
            }
        }
    }

    private void onWhisper(String user, String message) {
        if (twitchOwner.equalsIgnoreCase(user)) {
            sendMessage(message);
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
