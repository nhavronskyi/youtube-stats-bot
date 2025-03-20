package com.nhavronskyi.youtubestatsbot.service;

import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

public interface YouTubeService {
    List<SearchResult> search(String query);

    default List<String> searchLinks(String query) {
        return search(query).stream()
                .map(SearchResult::getId)
                .map(ResourceId::getVideoId)
                .map(id -> "https://www.youtube.com/watch?v=" + id)
                .toList();
    }
}
