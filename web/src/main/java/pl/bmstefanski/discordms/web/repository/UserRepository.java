package pl.bmstefanski.discordms.web.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.bmstefanski.discordms.web.entity.user.UserEntityImpl;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntityImpl, Long> {

}
