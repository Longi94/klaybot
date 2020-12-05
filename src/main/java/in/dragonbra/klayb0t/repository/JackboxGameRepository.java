package in.dragonbra.klayb0t.repository;

import in.dragonbra.klayb0t.entity.JackboxGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author lngtr
 * @since 2018-08-25
 */
public interface JackboxGameRepository extends JpaRepository<JackboxGame, Long> {

    @Query(value = "SELECT DISTINCT app_tag FROM jackbox_game WHERE created > NOW() - INTERVAL '6 HOUR' ORDER BY created DESC", nativeQuery = true)
    List<String> getRecentlyPlayed();

    @Query(value = "SELECT * FROM jackbox_game WHERE code = :code AND created > NOW() - INTERVAL '1 HOUR'", nativeQuery = true)
    List<JackboxGame> getRecentByCode(@Param("code") String code);

    @Query(value = "SELECT app_tag as appTag, count(*) as count, max(created) as lastPlayed FROM jackbox_game GROUP BY app_tag ORDER BY count DESC", nativeQuery = true)
    List<JackboxGameStat> getStats();
}
