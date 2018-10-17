package in.dragonbra.klayb0t.repository;

import java.util.Date;

/**
 * @author lngtr
 * @since 2018-09-03
 */
public interface JackboxGameStat {

    String getAppTag();

    int getCount();

    Date getLastPlayed();
}
