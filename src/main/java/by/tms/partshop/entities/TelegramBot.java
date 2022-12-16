package by.tms.partshop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Entity
@Table(name = "telegram_bot")
public class TelegramBot extends BaseEntity {

  @Column(name = "chat_id")
  private Long chatId;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;


}
