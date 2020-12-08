package in.dragonbra.klayb0t.repository;

import in.dragonbra.klayb0t.entity.SlapUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlapUserRepository extends JpaRepository<SlapUser, String> {
    List<SlapUser> findAllByExcludeFalse();
}
