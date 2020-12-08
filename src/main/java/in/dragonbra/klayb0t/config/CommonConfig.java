package in.dragonbra.klayb0t.config;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lngtr
 * @since 2018-08-15
 */
@Configuration
public class CommonConfig {

    @Value("classpath:game_mapping.json")
    private Resource gameMappingsResource;

    @Value("classpath:randomjack_messages")
    private Resource randomJackMessages;

    @Bean
    @Qualifier("randomjack_games")
    public Map<String, String> randomJackGames(Gson gson) throws IOException {
        Type type = new TypeToken<Map<String, String>>() {
        }.getType();
        return gson.fromJson(new InputStreamReader(gameMappingsResource.getInputStream()), type);
    }

    @Bean
    @Qualifier("randomjack_messages")
    public List<String> randomjackMesssages() throws IOException {
        return readLines(randomJackMessages);
    }

    private List<String> readLines(Resource r) throws IOException {
        Scanner scanner = new Scanner(r.getInputStream());

        List<String> messages = new ArrayList<>();

        while (scanner.hasNext()) {
            messages.add(scanner.nextLine());
        }

        scanner.close();

        return ImmutableList.copyOf(messages);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
