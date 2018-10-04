package in.dragonbra.klayb0t.service;

import in.dragonbra.klayb0t.entity.JackboxGame;
import in.dragonbra.klayb0t.repository.JackboxGameRepository;
import in.dragonbra.klayb0t.retrofit.JackboxInterface;
import in.dragonbra.klayb0t.retrofit.response.jackbox.JackboxRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * @author lngtr
 * @since 2018-10-04
 */
@Service
public class JackboxService {

    private static final Logger logger = LogManager.getLogger(JackboxService.class);

    private final JackboxInterface jackboxInterface;

    private final JackboxGameRepository jackboxGameRepository;

    @Autowired
    public JackboxService(JackboxInterface jackboxInterface,
                          JackboxGameRepository jackboxGameRepository) {
        this.jackboxInterface = jackboxInterface;
        this.jackboxGameRepository = jackboxGameRepository;
    }

    public void handle(String code) {
        logger.info("Handling code " + code);

        code = code.toUpperCase();

        List<JackboxGame> recentGames = jackboxGameRepository.getRecentByCode(code);

        if (recentGames.size() > 0) {
            // we had the same code in the past hour, very high chance its a duplicate
            logger.warn("Duplicate code detected");
            return;
        }

        Call<JackboxRoom> call = jackboxInterface.getRoom(code);

        Response<JackboxRoom> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            logger.warn("Failed to call jackbox api", e);
            return;
        }

        if (!response.isSuccessful()) {
            logger.warn("Unsuccessful call to jackbox: " + response.message());
            return;
        }

        JackboxRoom room = response.body();

        if (room == null) {
            logger.warn("Response body null");
            return;
        }

        if (room.getSuccess() != null && !room.getSuccess()) {
            logger.warn("Response unsuccessful response: " + room.getError());
            return;
        }

        JackboxGame game = new JackboxGame();

        game.setAppId(room.getAppId());
        game.setAppTag(room.getAppTag());
        game.setCode(room.getRoomId());
        game.setServer(room.getServer());

        jackboxGameRepository.save(game);
    }
}
