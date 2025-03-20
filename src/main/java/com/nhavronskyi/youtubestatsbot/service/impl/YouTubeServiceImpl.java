package com.nhavronskyi.youtubestatsbot.service.impl;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.nhavronskyi.youtubestatsbot.props.YoutubeProps;
import com.nhavronskyi.youtubestatsbot.service.YouTubeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeServiceImpl implements YouTubeService {
    private final YouTube youTube;
    private final YoutubeProps props;

    @SneakyThrows
    public List<SearchResult> search(String query) {
        var searchRequest = youTube.search().list("snippet");
        searchRequest.setKey(props.apiKey());
        searchRequest.setQ(query);
        searchRequest.setType("video");
        searchRequest.setPublishedAfter(new DateTime(System.currentTimeMillis() - 16 * 60 * 60 * 1000)); // 24 hours - 8 hours
        searchRequest.setMaxResults(3L);

        return searchRequest.execute()
                .getItems()
                .reversed();
    }
}
