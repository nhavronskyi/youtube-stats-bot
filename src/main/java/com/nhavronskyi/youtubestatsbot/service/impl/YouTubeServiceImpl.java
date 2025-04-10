package com.nhavronskyi.youtubestatsbot.service.impl;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.nhavronskyi.youtubestatsbot.props.GoogleProps;
import com.nhavronskyi.youtubestatsbot.service.YouTubeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeServiceImpl implements YouTubeService {
    private final YouTube youTube;
    private final GoogleProps props;

    @SneakyThrows
    public List<SearchResult> search(String query) {
        var searchRequest = youTube.search().list(List.of("snippet"));
        searchRequest.setKey(props.apiKey());
        searchRequest.setQ(query);
        searchRequest.setType(List.of("video"));
        searchRequest.setPublishedAfter(new DateTime(System.currentTimeMillis() - 16 * 60 * 60 * 1000).toString()); // 24 hours - 8 hours
        searchRequest.setMaxResults(3L);

        return searchRequest.execute()
                .getItems()
                .reversed();
    }

    @SneakyThrows
    @Override
    public List<String> getSubscriptions() {
        var request = youTube.subscriptions()
                .list(List.of("snippet"))
                .setMine(true)
                .setMaxResults(100L);

        return request.execute()
                .getItems()
                .stream()
                .map(x -> x.getSnippet().getTitle())
                .toList();
    }
}
