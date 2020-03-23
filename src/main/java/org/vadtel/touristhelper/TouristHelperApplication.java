package org.vadtel.touristhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class TouristHelperApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TouristHelperApplication.class, args);
    }

    @Bean
    public TelegramBotsApi BotReg(TelegramLongPollingBot bot) {
        ApiContextInitializer.init();
        TelegramBotsApi botReg = new TelegramBotsApi();
        try {
            botReg.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        return botReg;
    }

    @Bean
    public DefaultBotOptions botOptions() {
        return new DefaultBotOptions();
    }

}
