package pl.bmstefanski.discordms.web.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.bmstefanski.discordms.web.entity.user.UserEntityImpl;

@Repository
public interface UserRepository extends CrudRepository<UserEntityImpl, Long> {

}
