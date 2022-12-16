package by.tms.partshop.entities;

import by.tms.partshop.util.EmailConstraint;
import by.tms.partshop.util.LoginConstraint;
import by.tms.partshop.util.PasswordConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "users")
public class User extends BaseEntity {

  @Column(name = "login", nullable = false)
  @LoginConstraint
  private String login;
  @Column(name = "password", nullable = false)
  @PasswordConstraint
  private String password;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "surname", nullable = false)
  private String surname;
  @Column(name = "birthday", nullable = false)
  private LocalDate birthday;
  @Column(name = "email", nullable = false)
  @EmailConstraint
  private String email;
  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;
  @Column(name = "balance", nullable = false)
  private BigDecimal balance;
  @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
  private TelegramBot chatId;
  @ManyToOne(optional = false)
  @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
  private Role role;
}