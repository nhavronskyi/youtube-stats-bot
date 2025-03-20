package com.nhavronskyi.youtubestatsbot.service;

import com.google.api.services.youtube.model.SearchResult;
import com.nhavronskyi.youtubestatsbot.model.YouTubeResult;

import java.util.List;

public interface YouTubeService {
    List<SearchResult> search(String query);

    default List<YouTubeResult> searchLinks(String query) {
        return search(query).stream()
                .map(YouTubeResult::convert)
                .toList();
    }
}
