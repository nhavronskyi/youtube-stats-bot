package com.nhavronskyi.youtubestatsbot.service.impl;

import com.nhavronskyi.youtubestatsbot.props.UserProps;
import com.nhavronskyi.youtubestatsbot.service.BotService;
import com.nhavronskyi.youtubestatsbot.service.YouTubeService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {
    private final TelegramBot telegramBot;
    private final UserProps userProps;
    private final YouTubeService youTubeService;

    @Override
    public void sendMsgToAllUsers() {
        for (String chatId : userProps.chatIds()) {
            sendMsg(chatId, getMsg());
        }
    }

    private String getMsg() {
        return youTubeService.searchLinks("java").stream()
                .map(x -> x + " \n")
                .collect(Collectors.joining())
                .trim();

    }

    private void sendMsg(String chatId, String msg) {
        telegramBot.execute(new SendMessage(chatId, msg));
    }
}
