package com.nhavronskyi.youtubestatsbot;

import com.nhavronskyi.youtubestatsbot.service.YouTubeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class YoutubeStatsBotApplicationTests {
    @MockitoBean
    YouTubeService youTubeService;

    @Test
    void contextLoads() {
    }

}
