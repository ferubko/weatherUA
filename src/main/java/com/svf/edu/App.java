package com.svf.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by stepanferubko
 */
@SpringBootApplication
//public class App extends SpringBootServletInitializer implements CommandLineRunner {
//public class App extends SpringBootServletInitializer  {
public class App {
//    @Autowired
//    private MenuBot menuBot;

//    public App(MenuBot menuBot) {
//        this.menuBot = menuBot;
//    }

//    @Override
//    public void run(String... args) throws Exception {
//        TelegramBotsApi botsApi = new TelegramBotsApi();
//        try {
//            botsApi.registerBot(menuBot);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(App.class);
//    }

    public static void main(String[] args) {
//        ApiContextInitializer.init();
        SpringApplication.run(App.class, args);
    }
}
