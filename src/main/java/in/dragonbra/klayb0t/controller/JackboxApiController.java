package in.dragonbra.klayb0t.controller;

import in.dragonbra.klayb0t.entity.JackboxGame;
import in.dragonbra.klayb0t.repository.JackboxGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lngtr
 * @since 2018-08-28
 */
@RestController
@RequestMapping("/api/jackbox")
public class JackboxApiController {

    private final JackboxGameRepository jackboxGameRepository;

    @Autowired
    public JackboxApiController(JackboxGameRepository jackboxGameRepository) {
        this.jackboxGameRepository = jackboxGameRepository;
    }

    @GetMapping("/games")
    public List<JackboxGame> getGames() {
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        return this.jackboxGameRepository.findAll(sort);
    }
}
