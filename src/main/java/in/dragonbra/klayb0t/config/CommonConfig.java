package in.dragonbra.klayb0t.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lngtr
 * @since 2018-08-15
 */
@Configuration
public class CommonConfig {

    @Value("classpath:randomjack_games")
    private Resource randomJackGames;

    @Value("classpath:randomjack_messages")
    private Resource randomJackMessages;


    @Bean
    @Qualifier("randomjack_games")
    public List<String> randomJackGames() throws IOException {
        return readLines(randomJackGames);
    }

    @Bean
    @Qualifier("randomjack_messages")
    public List<String> hugOutcomes() throws IOException {
        return readLines(randomJackMessages);
    }

    private List<String> readLines(Resource r) throws IOException {
        Scanner scanner = new Scanner(r.getInputStream());

        List<String> messages = new ArrayList<>();

        while (scanner.hasNext()) {
            messages.add(scanner.nextLine());
        }

        scanner.close();

        return messages;
    }
}
