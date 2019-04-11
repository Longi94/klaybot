package in.dragonbra.klayb0t.command;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class RainbowsCommand extends Command {

    private static final Logger logger = LoggerFactory.getLogger(RainbowsCommand.class);

    // 5 mins
    private static final int RECONNECT_DELAY = 300000;

    @Value("${twitch.owner}")
    private String twitchOwner;
    @Value("${twitch.bot.channel}")
    private String twitchBotChannel;

    @Value("${twitch.bot.rainbow.red}")
    private String keyRed;
    @Value("${twitch.bot.rainbow.orange}")
    private String keyOrange;
    @Value("${twitch.bot.rainbow.yellow}")
    private String keyYellow;
    @Value("${twitch.bot.rainbow.green}")
    private String keyGreen;
    @Value("${twitch.bot.rainbow.blue}")
    private String keyBlue;
    @Value("${twitch.bot.rainbow.indigo}")
    private String keyIndigo;
    @Value("${twitch.bot.rainbow.violet}")
    private String keyViolet;

    private String[][] bots = new String[7][];

    private volatile boolean running = false;

    public RainbowsCommand() {
        super("rainbow");
    }

    @PostConstruct
    public void postConstruct() {
        bots[0] = new String[]{"rainbows_r", keyRed};
        bots[1] = new String[]{"rainbows_o", keyOrange};
        bots[2] = new String[]{"rainbows_y", keyYellow};
        bots[3] = new String[]{"rainbows_g", keyGreen};
        bots[4] = new String[]{"rainbows_b", keyBlue};
        bots[5] = new String[]{"rainbows_i", keyIndigo};
        bots[6] = new String[]{"rainbows_v", keyViolet};
    }

    @Override
    public String handle(User user, String message, String[] args) {
        if (running) {
            return null;
        }

        if (!twitchOwner.equalsIgnoreCase(user.getNick())) {
            return null;
        }

        running = true;

        CountDownLatch latch = new CountDownLatch(bots.length);

        List<PircBotX> pircBots = new ArrayList<>();

        for (String[] botArgs : bots) {
            if (botArgs == null) {
                continue;
            }

            PircBotX bot = createBot(botArgs[1], botArgs[0], latch);
            Thread bt = new Thread(new BotThread(bot));
            bt.start();

            pircBots.add(bot);
        }

        logger.info("Waiting for bots to connect...");

        try {
            boolean success = latch.await(10, TimeUnit.SECONDS);

            if (!success) {
                logger.error("count down latch timeout");
                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Sending rainbow messages...");

        for (PircBotX bot : pircBots) {
            if (bot != null) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bot.sendIRC().message("#" + twitchBotChannel, "/me RAINBOWS");
            }
        }

        logger.info("Disconnecting bots...");

        for (PircBotX bot : pircBots) {
            if (bot != null) {
                bot.sendIRC().quitServer();
                bot.stopBotReconnect();
            }
        }

        running = false;

        logger.info("Rainbows done.");

        return null;
    }

    private PircBotX createBot(String oauth, String name, CountDownLatch latch) {
        return new PircBotX(new Configuration.Builder()
                .setName(name)
                .addListener(new BotListener(latch))
                .addServer("irc.chat.twitch.tv", 6667)
                .setServerPassword(oauth)
                .addAutoJoinChannel("#" + twitchBotChannel)
                .setAutoReconnect(true)
                .setAutoReconnectDelay(RECONNECT_DELAY)
                .buildConfiguration());
    }

    private class BotThread implements Runnable {

        private PircBotX bot;

        private BotThread(PircBotX bot) {
            this.bot = bot;
        }

        @Override
        public void run() {
            try {
                bot.startBot();
            } catch (IOException | IrcException e) {
                e.printStackTrace();
            }
        }
    }

    private class BotListener extends ListenerAdapter {
        private CountDownLatch latch;

        private BotListener(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void onJoin(JoinEvent event) {
            latch.countDown();
        }
    }
}
