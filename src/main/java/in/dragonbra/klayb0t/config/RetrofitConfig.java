package in.dragonbra.klayb0t.config;

import in.dragonbra.klayb0t.retrofit.TwitchIdInterface;
import in.dragonbra.klayb0t.retrofit.TwitchInterface;
import in.dragonbra.klayb0t.retrofit.interceptor.HeaderInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${twitch.client-id}")
    private String twitchClientId;

    @Bean
    public TwitchInterface twitchInterface() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor("Client-ID", twitchClientId))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TwitchInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(TwitchInterface.class);
    }

    @Bean
    public TwitchIdInterface twitchIdInterface() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor("Client-ID", twitchClientId))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TwitchIdInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(TwitchIdInterface.class);
    }
}
