package com.nhavronskyi.youtubestatsbot.model;

import com.google.api.services.youtube.model.SearchResult;

public record YouTubeResult(String title, String link, String summary) {

    public static YouTubeResult convert(SearchResult searchResult) {
        return new YouTubeResult(searchResult.getSnippet().getTitle(),
                "https://www.youtube.com/watch?v=" + searchResult.getId().getVideoId(),
                searchResult.getSnippet().getDescription());
    }

    @Override
    public String toString() {
        return """
                Title: %s
                Link: %s
                Summary: %s
                """.formatted(title, link, summary);
    }
}
