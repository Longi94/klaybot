package in.dragonbra.klayb0t.controller;

import in.dragonbra.klayb0t.entity.JackboxGame;
import in.dragonbra.klayb0t.repository.JackboxGameRepository;
import in.dragonbra.klayb0t.repository.JackboxGameStat;
import in.dragonbra.klayb0t.service.JackboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lngtr
 * @since 2018-08-28
 */
@RestController
@RequestMapping("/api/jackbox")
public class JackboxApiController {

    private final JackboxGameRepository jackboxGameRepository;

    private final JackboxService jackboxService;

    private final Map<String, String> gameMapping;

    @Autowired
    public JackboxApiController(JackboxGameRepository jackboxGameRepository, JackboxService jackboxService,
                                @Qualifier("randomjack_games") Map<String, String> gameMapping) {
        this.jackboxGameRepository = jackboxGameRepository;
        this.jackboxService = jackboxService;
        this.gameMapping = gameMapping;
    }

    @GetMapping("/games")
    public List<JackboxGame> getGames() {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        return this.jackboxGameRepository.findAll(sort);
    }

    @PostMapping("/games")
    public ResponseEntity<Integer> addGame(@RequestParam("code") String code) {
        int result = this.jackboxService.handle(code);

        switch (result) {
            case 1:
            case 8:
                return ResponseEntity.badRequest().body(result);
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
            default:
                return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/games/mapping")
    public Map<String, String> getMapping() {
        return gameMapping;
    }

    @GetMapping("/games/stats")
    public List<JackboxGameStat> getGameStats() {
        return jackboxGameRepository.getStats();
    }
}
