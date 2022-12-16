package by.tms.partshop.telegramBot;

import static by.tms.partshop.util.constants.PatternConstants.EMAIL_PATTERN;
import static by.tms.partshop.util.constants.PatternConstants.PASSWORD_PATTERN;

import by.tms.partshop.entities.Order;
import by.tms.partshop.entities.TelegramBot;
import by.tms.partshop.entities.User;
import by.tms.partshop.repositories.OrderRepository;
import by.tms.partshop.repositories.TelegramRepository;
import by.tms.partshop.repositories.UserRepository;
import java.io.File;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
public class ShopTelegramBot extends TelegramLongPollingBot {

  private UserRepository userRepository;
  private OrderRepository orderRepository;
  private TelegramRepository telegramRepository;
  private PasswordEncoder passwordEncoder;

  @Override
  public String getBotUsername() {
    return "@PartShopTMSBot";
  }

  @Override
  public String getBotToken() {
    return "5340249889:AAHvdd1oOFkGrBd01IMrdNQPk84Dx5Rf3sk";
  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasCallbackQuery()) {
      try {
        handleCallback(update.getCallbackQuery());
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    } else if (update.hasMessage()) {
      try {
        handleMessage(update.getMessage());
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  private void handleCallback(CallbackQuery callbackQuery) throws TelegramApiException {
    String message = callbackQuery.getMessage().getText();

    String typeCommand = message.split(" ")[0];
    String command = message.split(" ")[1];

    switch (typeCommand) {
      case "confirmLogin" -> {

      }
    }
    execute(DeleteMessage.builder()
        .chatId(String.valueOf(callbackQuery.getMessage().getChatId()))
        .messageId(Integer.valueOf(callbackQuery.getId()))
        .build());
  }

  private void handleMessage(Message message) throws TelegramApiException {
    if (message.hasText() && message.hasEntities()) {
      Optional<MessageEntity> commandEntities =
          message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();

      String messageParameter;
      try {
        messageParameter = message.getText().split(" ")[1];
      } catch (Exception e) {
        messageParameter = "";
      }

      if (commandEntities.isPresent()) {
        String command = message.getText()
            .substring(commandEntities.get().getOffset(), commandEntities.get().getLength());
        switch (command) {
          case "/start" -> {
            execute(SendMessage.builder()
                .text("""
                    Вас приветствует телеграм-бот магазина!
                    Для взаимодействия с ботом Вы можете использовать следующие команды:
                    /connect - Привязать бот к аккаунту
                    /orders - Ваши заказы
                    /ordersfile - Получить выгрузку информации о всех заказах
                    /changepass *новый пароль* - Изменить пароль
                    /changemail *новый E-Mail* - Изменить E-Mail
                    /help - Помощь
                    """)
                .chatId(message.getChatId().toString())
                .build());
          }
          case "/connect" -> {
            try {
              TelegramBot telegramBot = TelegramBot.builder()
                  .chatId(message.getChatId())
                  .build();
              telegramRepository.save(telegramBot);
              execute(SendMessage.builder()
                  .text("Вы успешно подключили бота! Ваш код: " + message.getChatId())
                  .chatId(message.getChatId().toString())
                  .build());
            } catch (Exception e) {
              execute(SendMessage.builder()
                  .text("Ошибка! Данный пользователь уже зарегистрирован!")
                  .chatId(message.getChatId().toString())
                  .build());
            }
          }
          case "/orders" -> {
            User user = userRepository.getUserByChatId_ChatId(message.getChatId());
            List<Order> orders = orderRepository.getAllByUser(user);
            for (Order order : orders) {
              execute(SendMessage.builder()
                  .text("Заказ №" + order.getId() + "\n"
                      + "Дата заказа: " + order.getOrderDate() + "\n"
                      + "Сумма заказа: " + order.getOrderPrice() + "$")
                  .chatId(message.getChatId().toString())
                  .build());
            }
          }
          case "/changepass" -> {
            User user = userRepository.getUserByChatId_ChatId(message.getChatId());
            if (messageParameter == null) {
              execute(SendMessage.builder()
                  .text("Введите комманду /changepass *новый пароль*")
                  .chatId(message.getChatId().toString())
                  .build());
              return;
            }
            if (messageParameter.matches(PASSWORD_PATTERN)) {
              user.setPassword(passwordEncoder.encode(messageParameter));
              userRepository.save(user);
              execute(SendMessage.builder()
                  .text("Ваш пароль успешно изменён!")
                  .chatId(message.getChatId().toString())
                  .build());
            } else {
              execute(SendMessage.builder()
                  .text("Пароль не соответствует требованиям! \n "
                      + "Пароль должен содержать минимум одно число, один спецсимвол \"?=.*[!@#$%^&*]\", "
                      + "одну латинскую заглавную и строчную букву латинского алфавита. Пароль должен быть от 6 до 20 символов.")
                  .chatId(message.getChatId().toString())
                  .build());
            }
          }
          case "/changemail" -> {
            User user = userRepository.getUserByChatId_ChatId(message.getChatId());
            if (messageParameter == null) {
              execute(SendMessage.builder()
                  .text("Введите комманду /changeMail *новый E-Mail*")
                  .chatId(message.getChatId().toString())
                  .build());
              return;
            }
            if (messageParameter.matches(EMAIL_PATTERN)) {
              user.setEmail(messageParameter);
              userRepository.save(user);
              execute(SendMessage.builder()
                  .text("Ваш Email успешно изменён!")
                  .chatId(message.getChatId().toString())
                  .build());
            } else {
              execute(SendMessage.builder()
                  .text("Email не соответствует требованиям!")
                  .chatId(message.getChatId().toString())
                  .build());
            }
          }

          case "/ordersfile" -> {
            execute(SendMessage.builder()
                .text("""
                    Извините, но данная фунция находится в разработке!
                    """)
                .chatId(message.getChatId().toString())
                .build());
          }

          case "/help" -> {
            execute(SendMessage.builder()
                .text("""
                    Для взаимодействия с ботом Вы можете использовать следующие команды:
                    /connect - Привязать бот к аккаунту
                    /orders - Ваши заказы
                    /ordersfile - Получить выгрузку информации о всех заказах
                    /changepass *новый пароль* - Изменить пароль
                    /changemail *новый E-Mail* - Изменить E-Mail
                    /help - Помощь
                    """)
                .chatId(message.getChatId().toString())
                .build());
          }
        }
      }
    }
  }

  public void sendMessageToChat(long chatId, String message) throws TelegramApiException {
    execute(SendMessage.builder()
        .chatId(String.valueOf(chatId))
        .text(message)
        .build());
  }

  public void sendDocumentToChat(long chatId, File file) throws TelegramApiException {
    execute(SendDocument.builder()
        .document(new InputFile(file))
        .chatId(String.valueOf(chatId))
        .caption("*message*")
        .build());
  }

  public void sendInlineButtonsToChat(List<List<InlineKeyboardButton>> buttons, String message,
      long chatId) throws TelegramApiException {
    execute(SendMessage.builder()
        .text(message)
        .chatId(String.valueOf(chatId))
        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
        .build());
  }
}
