package org.vadtel.touristhelper.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.vadtel.touristhelper.dto.CityDto;
import org.vadtel.touristhelper.dto.CityInfoDto;
import org.vadtel.touristhelper.exception.CityNotFoundException;
import org.vadtel.touristhelper.service.CityInfoService;
import org.vadtel.touristhelper.service.CityService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TouristBot extends TelegramLongPollingBot {

    private final CityService cityService;
    private final CityInfoService cityInfoService;
    @Value("${telegram.botusername}")
    private String botUSerName;
    @Value("${telegram.token}")
    private String botToken;

    @Autowired
    public TouristBot(DefaultBotOptions options, CityService cityService, CityInfoService cityInfoService) {
        super(options);
        this.cityService = cityService;
        this.cityInfoService = cityInfoService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() && !update.getMessage().hasText()) {
            return;
        }
        log.info(update.toString());
        sendMsg(update.getMessage().getChatId().toString(), update);
    }

    private void sendMsg(String chatId, Update update) {
        SendMessage sendMessage = new SendMessage();
        String message = "";
        if (update.hasCallbackQuery()) {

            CallbackQuery callbackQuery = update.getCallbackQuery();
            message = callbackQuery.getData();
        } else {
            message = update.getMessage().getText();
        }

        String send = "";
        if (message.equals("/start") || message.equals("/Start")) {
            String firstName = update.getMessage().getChat().getFirstName();
            String lastName = update.getMessage().getChat().getFirstName();
            send = String.format("Добро пожаловать %s %s %nТуристический помощник подскажет Вам лучшие места для посещения %nВведите название города",
                    firstName, lastName);
        }
        try {
            List<CityInfoDto> allCityInfo = cityInfoService.getAllCityInfoByCityName(message.strip());
            List<String> infos = allCityInfo.stream().map(s -> s.getCityInfo()).collect(Collectors.toList());
            send = String.join(".\n", infos);
        } catch (CityNotFoundException e) {
            List<CityDto> citiesMeaning = cityService.getCitiesByNameContaining(message.strip());
            if (!citiesMeaning.isEmpty()) {
                List<String> citiesName = citiesMeaning.stream().map(s -> s.getCityName()).collect(Collectors.toList());
                send = "Возможно вы ввели не правильно город.\nВыберете подходящий:";
                sendMessage.setReplyMarkup(setInline(citiesName));
            } else {
                send = "У нас в базе нет информации о таком городе";
            }
        }

        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(send);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private ReplyKeyboardMarkup setInline(List<String> cities) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        for(String city:cities) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton(city));
            keyboard.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
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
