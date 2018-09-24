package in.dragonbra.klayb0t.chat;

import in.dragonbra.klayb0t.entity.JackboxGame;
import in.dragonbra.klayb0t.repository.JackboxGameRepository;
import in.dragonbra.klayb0t.retrofit.JackboxInterface;
import in.dragonbra.klayb0t.retrofit.response.jackbox.JackboxRoom;
import org.pircbotx.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lngtr
 * @since 2018-08-25
 */
@Component
public class JackboxCodeHandler extends MessageHandler {

    private static final Pattern CODE_PATTERN = Pattern.compile("^(.*[^A-Z])?(<?code>[A-Z]{4})(.*[^A-Z])?$");

    @Value("${twitch.bot.channel}")
    private String twitchBotChannel;

    private final JackboxInterface jackboxInterface;

    private final JackboxGameRepository jackboxGameRepository;

    @Autowired
    public JackboxCodeHandler(JackboxInterface jackboxInterface, JackboxGameRepository jackboxGameRepository) {
        this.jackboxInterface = jackboxInterface;
        this.jackboxGameRepository = jackboxGameRepository;
    }

    @Override
    public String handle(User user, String message) throws IOException {
        if (!twitchBotChannel.equalsIgnoreCase(user.getLogin())) {
            return null;
        }

        Matcher matcher = CODE_PATTERN.matcher(message);
        if (!matcher.matches()) {
            return null;
        }

        String code = matcher.group("code");

        Call<JackboxRoom> call = jackboxInterface.getRoom(code);

        Response<JackboxRoom> response = call.execute();

        if (!response.isSuccessful()) {
            return null;
        }

        JackboxRoom room = response.body();

        if (room == null) {
            return null;
        }

        if (room.getSuccess() != null && !room.getSuccess()) {
            return null;
        }

        JackboxGame game = new JackboxGame();

        game.setAppId(room.getAppId());
        game.setAppTag(room.getAppTag());
        game.setCode(room.getRoomId());
        game.setServer(room.getServer());

        jackboxGameRepository.save(game);

        return null;
    }
}
