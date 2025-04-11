package com.nhavronskyi.youtubestatsbot.service.impl;

import com.nhavronskyi.youtubestatsbot.props.UserProps;
import com.nhavronskyi.youtubestatsbot.service.BotService;
import com.nhavronskyi.youtubestatsbot.service.YouTubeService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {
    private final TelegramBot telegramBot;
    private final UserProps userProps;
    private final YouTubeService youTubeService;
    private final GeminiService geminiService;

    @Override
    public void sendMsgToAllUsers() {
        for (String chatId : userProps.chatIds()) {
            sendMsg(chatId, getMsg());
        }
    }

    private String getMsg() {
        return geminiService.getSummaryAboutSubscribers(youTubeService.getSubscriptions());
    }

    private void sendMsg(String chatId, String msg) {
        telegramBot.execute(new SendMessage(chatId, msg)
                .parseMode(ParseMode.Markdown)
        );
    }
}
