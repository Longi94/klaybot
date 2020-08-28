package in.dragonbra.klayb0t.config;

import in.dragonbra.klayb0t.command.*;
import in.dragonbra.klayb0t.manager.CommandManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lngtr
 * @since 2018-01-27
 */
@Configuration
public class TwitchBotConfig {

    @Bean
    public CommandManager commandManager(RandomJackboxCommand randomJackboxCommand,
                                         TodayCommand todayCommand,
                                         RainbowsCommand rainbowsCommand,
                                         SlapCommand slapCommand) {
        CommandManager manager = new CommandManager();

        manager.registerCommand(new SimpleResponseCommand("ping", "ping", "pong!"));

        manager.registerCommand(randomJackboxCommand);
        manager.registerCommand(todayCommand);
        manager.registerCommand(rainbowsCommand);
        manager.registerCommand(new PlayCommand());
        manager.registerCommand(slapCommand);

        return manager;
    }
}
