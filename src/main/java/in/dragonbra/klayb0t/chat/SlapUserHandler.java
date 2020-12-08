package in.dragonbra.klayb0t.chat;

import in.dragonbra.klayb0t.entity.SlapUser;
import in.dragonbra.klayb0t.repository.SlapUserRepository;
import org.pircbotx.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SlapUserHandler extends MessageHandler {

    private static final long CACHE_EVICTION_INTERVAL = 1000 * 60 * 60;

    private final SlapUserRepository slapUserRepository;

    private final Set<String> cache = new HashSet<>();

    private long lastCacheEviction = 0L;

    public SlapUserHandler(SlapUserRepository slapUserRepository) {
        this.slapUserRepository = slapUserRepository;
    }

    @Override
    public String handle(User user, String message) {
        String username = user.getNick();

        if (cache.contains(username)) {
            evictCache();
            return null;
        }

        if (!slapUserRepository.existsById(username)) {
            SlapUser newUser = new SlapUser(username);
            slapUserRepository.save(newUser);
        }
        cache.add(username);
        evictCache();
        return null;
    }

    private void evictCache() {
        long currentTs = System.currentTimeMillis();
        if (lastCacheEviction + CACHE_EVICTION_INTERVAL < currentTs) {
            return;
        }

        cache.clear();
        lastCacheEviction = currentTs;
    }
}
