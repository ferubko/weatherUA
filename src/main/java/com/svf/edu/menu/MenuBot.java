package com.svf.edu.menu;

import com.svf.edu.common.CityEnum;
import com.svf.edu.common.InlineKeyboardBuilder;
import com.svf.edu.dto.WeatherValue;
import com.svf.edu.service.BotClientService;
import com.svf.edu.service.MessageService;
import com.svf.edu.service.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.toIntExact;

/**
 * Created by stepanferubko
 */
@Component
public class MenuBot extends TelegramLongPollingBot {
    private final static Logger LOG = Logger.getLogger(MenuBot.class.getName());

    @Autowired
    private MenuManager menuManager;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private BotClientService botClientService;

    @Value("${telegram.boot.name}")
    private String BOT_NAME;
    @Value("${telegram.boot.token}")
    private String API_KEY;

    public void setBOT_NAME(String BOT_NAME) {
        this.BOT_NAME = BOT_NAME;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    @PostConstruct
    public void init() {
        menuManager.setColumnsCount(2);
        CityEnum.cities().forEach(c -> menuManager.addMenuItem(c.getCityName(), c.getValue()));
        menuManager.init();
    }

    private void replaceMessageWithText(long chatId, long messageId, String text) {
        EditMessageText newMessage = new EditMessageText()
                .setChatId(chatId)
                .setMessageId(toIntExact(messageId))
                .setText(text);
        executeSendingEditMessage(newMessage);
    }

    private void replaceMessage(long chatId, long messageId, SendMessage message) {
        EditMessageText newMessage = new EditMessageText()
                .setChatId(chatId)
                .setMessageId(toIntExact(messageId))
                .setText(message.getText())
                .setReplyMarkup((InlineKeyboardMarkup) message.getReplyMarkup());
        executeSendingEditMessage(newMessage);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String userName;
            if (update.getMessage().getFrom().getFirstName() != null) {
                userName = update.getMessage().getFrom().getFirstName();
            } else if (update.getMessage().getFrom().getUserName() != null) {
                userName = update.getMessage().getFrom().getUserName();
            } else userName = "Anonymous";
            if (botClientService.findByChatId(chatId) == null) {
                botClientService.saveBotClient(chatId, userName);
            }
            String previousCommand = botClientService.getPreviousCommand(chatId);
            if (update.getMessage().hasText()) {
                String messageFromUser = update.getMessage().getText();
                if (messageFromUser.equals("/start") || messageFromUser.equals("/menu")) {
                    createNavigationButtons(chatId);
                    botClientService.setPreviousCommand(chatId, messageFromUser);
                } else if (previousCommand.equals("Введіть назву міста:") && !messageFromUser.equals("Обрати зі списку")) {
                    if (messageFromUser.equals("Введіть назву міста:")) {
                        SendMessage message = InlineKeyboardBuilder.create(chatId).setText("Ви не ввели потрібну інформацію спробуйте ще раз").build();
                        executeSendingMessage(message);
                        return;
                    }
                    CityEnum city = CityEnum.getByName(messageFromUser);
                    if (city != null) {
                        sendCityMessage(chatId, city);
                        LOG.log(Level.INFO, "Sent information from input data");
                    } else {
                        SendMessage message = InlineKeyboardBuilder.create(chatId).setText("Вибачте\nдане місто не знайдено в базі").build();
                        executeSendingMessage(message);
                        return;
                    }
                    System.out.println("ви обрали місто " + messageFromUser);
                } else if (messageFromUser.equals("Введіть назву міста:")) {
                    System.out.println("ви обрали місто " + messageFromUser);
                    botClientService.setPreviousCommand(chatId, messageFromUser);
                } else if (messageFromUser.equals("Обрати зі списку")) {
                    InlineKeyboardBuilder builder = menuManager.createMenuForPage(0, true);
                    builder.setChatId(chatId).setText("Виберіть місто:");
                    SendMessage message = builder.build();
                    executeSendingMessage(message);
                    botClientService.setPreviousCommand(chatId, messageFromUser);
                }
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Message receivedMess = callbackQuery.getMessage();
            long chatId = receivedMess.getChatId();
            long messageId = receivedMess.getMessageId();
            String callData = callbackQuery.getData();
            CityEnum city = CityEnum.getByValue(callData);
            // here will be menu buttons callbacks
            if (callData.equals(MenuManager.CANCEL_ACTION)) {
                replaceMessageWithText(chatId, messageId, "Відмінено.");
            } else if (city != null) {
                sendCityMessage(chatId, city);
                LOG.log(Level.INFO, "Sent information from menu");
            } else if (callData.startsWith(MenuManager.PREV_ACTION) || callData.startsWith(MenuManager.NEXT_ACTION)) {
                String pageNum = "0";
                if (callData.startsWith(MenuManager.PREV_ACTION)) {
                    pageNum = callData.replace(MenuManager.PREV_ACTION + ":", "");
                } else {
                    pageNum = callData.replace(MenuManager.NEXT_ACTION + ":", "");
                }
                InlineKeyboardBuilder builder = menuManager.createMenuForPage(Integer.parseInt(pageNum), true);
                builder.setChatId(chatId).setText("Оберіть місто:");
                SendMessage message = builder.build();
                replaceMessage(chatId, messageId, message);
            }
        }
    }

    private void createNavigationButtons(long chat_id) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chat_id)
                .setText("Оберіть дію:");
        ReplyKeyboardMarkup markupInline = new ReplyKeyboardMarkup()
                .setSelective(true)
                .setResizeKeyboard(true)
                .setOneTimeKeyboard(true);
        List<KeyboardRow> rowInline = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton list = new KeyboardButton("Обрати зі списку");
        list.setRequestContact(false);
        KeyboardButton setCity = new KeyboardButton("Введіть назву міста");
        setCity.setRequestContact(false);
        row.add(list);
        row.add(setCity);
        rowInline.add(row);
        markupInline.setKeyboard(rowInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendCityMessage(long chatId, CityEnum city) {
        synchronized (this) {
            WeatherValue cityWeather = weatherService.getCityWeather(city.getValue());
            String messageText = messageService.combineMessage(city, cityWeather);
            SendMessage message = InlineKeyboardBuilder.create(chatId).setText(messageText).build();
            executeSendingMessage(message);
            LOG.log(Level.INFO, "Message was sent to " + chatId);
        }
    }

    private void executeSendingMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void executeSendingEditMessage(EditMessageText message) {
        try {
            // Send the message
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return API_KEY;
    }
}
