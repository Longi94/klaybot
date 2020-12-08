package in.dragonbra.klayb0t.service;

import in.dragonbra.klayb0t.retrofit.TwitchTmiInterface;
import in.dragonbra.klayb0t.retrofit.response.TwitchChatters;
import in.dragonbra.klayb0t.retrofit.response.TwitchChattersResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class TwitchService {

    @Value("${twitch.bot.channel}")
    private String channel;

    private final TwitchTmiInterface twitchTmiInterface;

    public TwitchService(TwitchTmiInterface twitchTmiInterface) {
        this.twitchTmiInterface = twitchTmiInterface;
    }

    public Set<String> getViewerNames() throws IOException {
        Response<TwitchChattersResponse> response = twitchTmiInterface.getChatters(channel).execute();

        if (!response.isSuccessful()) {
            return null;
        }

        TwitchChattersResponse body = response.body();

        if (body == null) {
            return null;
        }

        Set<String> names = new HashSet<>();

        TwitchChatters chatters = body.getChatters();
        names.addAll(chatters.getAdmins());
        names.addAll(chatters.getBroadcaster());
        names.addAll(chatters.getGlobalMods());
        names.addAll(chatters.getModerators());
        names.addAll(chatters.getStaff());
        names.addAll(chatters.getViewers());
        names.addAll(chatters.getVips());

        return names;
    }
}
