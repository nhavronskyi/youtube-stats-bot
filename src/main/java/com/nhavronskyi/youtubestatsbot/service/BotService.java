package com.nhavronskyi.youtubestatsbot.service;

public interface BotService {
    void sendMsg(String chatId, String msg);

    void sendMsgToAllUsers(String msg);
}
