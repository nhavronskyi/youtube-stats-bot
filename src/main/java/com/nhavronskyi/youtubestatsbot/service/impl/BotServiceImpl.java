package com.nhavronskyi.youtubestatsbot.service.impl;

import com.nhavronskyi.youtubestatsbot.props.UserProps;
import com.nhavronskyi.youtubestatsbot.service.BotService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {
    private final TelegramBot telegramBot;
    private final UserProps userProps;

    @Override
    public void sendMsg(String chatId, String msg) {
        telegramBot.execute(new SendMessage(chatId, msg));
    }

    @Override
    public void sendMsgToAllUsers(String msg) {
        for (String chatId : userProps.chatIds()) {
            telegramBot.execute(new SendMessage(chatId, msg));
        }
    }
}
