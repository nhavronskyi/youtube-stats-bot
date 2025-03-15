package com.nhavronskyi.youtubestatsbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class YoutubeStatsBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoutubeStatsBotApplication.class, args);
    }

}
