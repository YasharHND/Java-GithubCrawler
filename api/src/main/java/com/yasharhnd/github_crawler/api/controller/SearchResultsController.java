package com.yasharhnd.github_crawler.api.controller;

import com.yasharhnd.github_crawler.api.client.DatastoreMsClient;
import com.yasharhnd.github_crawler.commons.dto.ReqSearchResultsDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/search-results")
public class SearchResultsController {

    private final DatastoreMsClient datastoreMsClient;

    @PostMapping
    public ResponseEntity<Page<Object>> searchResults(@Valid @RequestBody final ReqSearchResultsDto dto, @RequestParam final Integer page) {
        return ResponseEntity.ok(datastoreMsClient.searchResults(dto, page));
    }

}
