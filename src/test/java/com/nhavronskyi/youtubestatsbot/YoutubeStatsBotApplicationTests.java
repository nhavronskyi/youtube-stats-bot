package com.nhavronskyi.youtubestatsbot;

import com.nhavronskyi.youtubestatsbot.service.BotService;
import com.nhavronskyi.youtubestatsbot.service.YouTubeService;
import com.nhavronskyi.youtubestatsbot.service.impl.GeminiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class YoutubeStatsBotApplicationTests {
    @MockitoBean
    YouTubeService youTubeService;
    @MockitoBean
    GeminiService geminiService;
    @MockitoBean
    BotService botService;

    @Test
    void contextLoads() {
    }

}
