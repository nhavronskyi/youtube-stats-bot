package com.nhavronskyi.youtubestatsbot.model;

import com.google.api.services.youtube.model.SearchResult;

public record YouTubeResult(String title, String channelName, String link, String description) {

    public static YouTubeResult convert(SearchResult searchResult) {
        return new YouTubeResult(searchResult.getSnippet().getTitle(),
                searchResult.getSnippet().getChannelTitle(),
                "https://www.youtube.com/watch?v=" + searchResult.getId().getVideoId(),
                searchResult.getSnippet().getDescription());
    }

    @Override
    public String toString() {
        return """
                Title: %s
                Channel: %s
                Link: %s
                description: %s
                
                
                """.formatted(title, channelName, link, description);
    }
}
