package com.nhavronskyi.youtubestatsbot.service.impl;

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
        searchRequest.setMaxResults(5L);

        return searchRequest.execute()
                .getItems();
    }
}
