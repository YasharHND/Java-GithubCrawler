package com.yasharhnd.github_crawler.api.controller;

import com.yasharhnd.github_crawler.api.dto.PageObject;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/search-results")
public class SearchResultsController {

    private final RestTemplate datastoreMsClient;

    @PostMapping
    public ResponseEntity<Page<Object>> searchResults(@Valid @RequestBody final ReqSearchResultsDto dto, @RequestParam final Integer page) {
        final var uri = UriComponentsBuilder.fromUriString("/search-results").queryParam("page", page).toUriString();
        final var result = datastoreMsClient.postForEntity(uri, dto, PageObject.class);
        return ResponseEntity.ok(result.getBody());
    }

}
