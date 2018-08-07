package in.dragonbra.klayb0t.config;

import in.dragonbra.klayb0t.command.SimpleResponseCommand;
import in.dragonbra.klayb0t.command.SubbedCommand;
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
    public CommandManager commandManager(SubbedCommand subbedCommand) {
        CommandManager manager = new CommandManager();

        manager.registerCommand(new SimpleResponseCommand("ping", "ping", "pong!"));

        manager.registerCommand(subbedCommand);

        return manager;
    }
}
