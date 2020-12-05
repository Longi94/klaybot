package in.dragonbra.klayb0t.config;

import in.dragonbra.klayb0t.retrofit.JackboxInterface;
import in.dragonbra.klayb0t.retrofit.TwitchTmiInterface;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author lngtr
 * @since 2017-12-31
 */
@Configuration
public class RetrofitConfig {

    @Bean
    public JackboxInterface jackboxInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JackboxInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        return retrofit.create(JackboxInterface.class);
    }

    @Bean
    public TwitchTmiInterface twitchTmiInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TwitchTmiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        return retrofit.create(TwitchTmiInterface.class);
    }
}
