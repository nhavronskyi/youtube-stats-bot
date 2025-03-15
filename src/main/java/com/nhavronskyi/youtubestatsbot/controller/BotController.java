package com.nhavronskyi.youtubestatsbot.controller;

import com.nhavronskyi.youtubestatsbot.props.UserProps;
import com.nhavronskyi.youtubestatsbot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BotController {
    private final BotService botService;
    private final UserProps userProps;

    @PostMapping
    public void sendMsg() {
        for (String s : userProps.chatIds()) {
            botService.sendMsg(s, "hello");
        }
    }
}
