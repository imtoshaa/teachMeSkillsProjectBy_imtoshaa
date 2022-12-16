package by.tms.partshop.repositories;

import by.tms.partshop.entities.TelegramBot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramRepository extends JpaRepository<TelegramBot, Long> {
    TelegramBot getByChatId (long chatId);
}
