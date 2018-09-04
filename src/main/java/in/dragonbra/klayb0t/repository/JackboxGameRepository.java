package in.dragonbra.klayb0t.repository;

import in.dragonbra.klayb0t.entity.JackboxGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lngtr
 * @since 2018-08-25
 */
public interface JackboxGameRepository extends JpaRepository<JackboxGame, Long> {

    @Query(value = "SELECT DISTINCT app_tag FROM jackbox_game WHERE created > date_add(now(), INTERVAL -6 HOUR) ORDER BY created DESC", nativeQuery = true)
    public List<String> getRecentlyPlayed();
}
