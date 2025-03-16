package com.nhavronskyi.youtubestatsbot;

import com.nhavronskyi.youtubestatsbot.service.BotService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@RequiredArgsConstructor
public class YoutubeStatsBotApplication {
    private final BotService botService;

    public static void main(String[] args) {
        SpringApplication.run(YoutubeStatsBotApplication.class, args);
    }

    @PostConstruct
    public void init() {
        botService.sendMsgToAllUsers("Hello World!");
    }

}
