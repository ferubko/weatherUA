package com.svf.edu.config;

import com.svf.edu.menu.MenuManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by stepanferubko
 */
@Configuration
//@ComponentScan(basePackages = "com.svf.edu")
public class BotConfig {
    @Bean
    public MenuManager menuManager() {
        return new MenuManager();
    }

    @Bean
    public TelegramBotsApiBean telegramBotsApiBean() {
        return new TelegramBotsApiBean();
    }
}
