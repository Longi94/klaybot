package in.dragonbra.klayb0t.repository;

import in.dragonbra.klayb0t.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByTwitchId(String twitchId);

    User findByUsername(String username);
}
