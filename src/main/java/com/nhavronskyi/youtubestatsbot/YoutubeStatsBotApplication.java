package com.nhavronskyi.youtubestatsbot;

import com.nhavronskyi.youtubestatsbot.service.BotService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
@RequiredArgsConstructor
public class YoutubeStatsBotApplication {
    private final BotService botService;

    @PostConstruct
    public void init() {
        botService.sendMsgToAllUsers("Hello World!");
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(YoutubeStatsBotApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
