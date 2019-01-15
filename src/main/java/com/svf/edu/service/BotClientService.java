package com.svf.edu.service;

import com.svf.edu.repository.BotClientRepository;
import com.svf.edu.vo.BotClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by stepanferubko
 */
@Service
public class BotClientService {
    @Autowired
    private BotClientRepository botClientRepository;

    public BotClient findByChatId(long chatId) {
        return botClientRepository.findByChatId(chatId);
    }

    public void saveBotClient(long chatId, String name) {
        BotClient subscriber = new BotClient();
        subscriber.setChatId(chatId);
        subscriber.setClientName(name);
        botClientRepository.save(subscriber);
    }

    public void updateBotClient(BotClient botClient) {
        botClientRepository.save(botClient);
    }

    public BotClient findBotClient(long id) {
        BotClient one = botClientRepository.findOne(id);
        if (one != null) {
            return one;
        } else {
            return new BotClient();
        }
    }

    public List<BotClient> getAllChatUsers() {
        return botClientRepository.findAll();
    }

    public void setPreviousCommand(long chatId, String command) {
        BotClient botClient = botClientRepository.findByChatId(chatId);
        botClient.setPreviousCommand(command);
        botClientRepository.save(botClient);
    }

    public String getPreviousCommand(long chatId) {
        BotClient subscriber = botClientRepository.findByChatId(chatId);
        if (subscriber.getPreviousCommand() != null) {
            return subscriber.getPreviousCommand();
        } else {
            subscriber.setPreviousCommand("/start");
        }
        return "/start";
    }
}
