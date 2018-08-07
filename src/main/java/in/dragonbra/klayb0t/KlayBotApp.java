package in.dragonbra.klayb0t;

import in.dragonbra.klayb0t.bot.TwitchBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author lngtr
 * @since 2018-08-07
 */
@SpringBootApplication
public class KlayBotApp {
    public static void main(String[] args) {
        SpringApplication.run(KlayBotApp.class, args);
    }

    private final TwitchBot twitchBot;

    public KlayBotApp(TwitchBot twitchBot) {
        this.twitchBot = twitchBot;
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> twitchBot.start();
    }
}
