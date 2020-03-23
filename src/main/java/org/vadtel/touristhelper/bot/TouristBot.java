package org.vadtel.touristhelper.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.vadtel.touristhelper.dto.CityDto;
import org.vadtel.touristhelper.service.CityService;

@Component
public class TouristBot extends TelegramLongPollingBot {

    @Value("${telegram.botusername}")
    private String botUSerName;
    @Value("${telegram.token}")
    private String botToken;

    private final CityService cityService;

    @Autowired
    public TouristBot(DefaultBotOptions options, CityService cityService) {
        super(options);
        this.cityService = cityService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() && !update.getMessage().hasText()) {
            return;
        }
        String city = update.getMessage().getText();
        CityDto cityById = cityService.getCityById(Long.parseLong(city));


        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(cityById.toString());

        try {
            this.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String getBotUsername() {
        return botUSerName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
