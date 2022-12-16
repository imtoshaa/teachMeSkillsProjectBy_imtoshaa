package by.tms.partshop.repositories;

import by.tms.partshop.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User getUserByLogin(String string);

  User getUserByChatId_ChatId(Long chatId);

  Optional<User> findUserByLogin(String login);

  User getUserById(long id);
}
