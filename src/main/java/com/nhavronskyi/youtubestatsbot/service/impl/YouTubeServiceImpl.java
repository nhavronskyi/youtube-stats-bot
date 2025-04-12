package com.nhavronskyi.youtubestatsbot.service.impl;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.Video;
import com.nhavronskyi.youtubestatsbot.model.YouTubeResult;
import com.nhavronskyi.youtubestatsbot.service.YouTubeService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeServiceImpl implements YouTubeService {
    private final YouTube youTube;

    @SneakyThrows
    public List<SearchResult> search(String query) {
        var searchRequest = youTube.search().list(List.of("snippet"));
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
    public List<YouTubeResult> getLatestVideosFromSubscriptions() {
        var subscriptions = getSubscriptions();

        return subscriptions.stream()
                .map(subscription -> subscription.getSnippet()
                        .getResourceId()
                        .getChannelId())
                .map(this::getLatestVideosFromTheChannel)
                .flatMap(List::stream)
                .map(YouTubeResult::convert)
                .toList();
    }

    @SneakyThrows
    private List<Subscription> getSubscriptions() {
        var request = youTube.subscriptions()
                .list(List.of("snippet"))
                .setMine(true)
                .setMaxResults(50L);

        return request.execute()
                .getItems();
    }

    @SneakyThrows
    private List<SearchResult> getLatestVideosFromTheChannel(String channelId) {
        List<SearchResult> videos = youTube.search()
                .list(List.of("snippet"))
                .setChannelId(channelId)
                .setOrder("date")
                .setPublishedAfter(new DateTime(System.currentTimeMillis() - 16 * 60 * 60 * 1000).toString())
                .setType(List.of("video"))
                .setMaxResults(3L)
                .execute()
                .getItems();

        var nonShortVideoIds = youTube.videos()
                .list(List.of("snippet", "contentDetails"))
                .setId(videos.stream()
                        .map(x -> x.getId().getVideoId())
                        .toList())
                .execute()
                .getItems()
                .stream()
                .filter(x -> !x.getContentDetails().getDuration().matches("PT[0-5]?[0-9]S"))
                .map(Video::getId)
                .toList();

        return videos.stream()
                .filter(video -> nonShortVideoIds.contains(video.getId().getVideoId()))
                .toList();
    }
}
