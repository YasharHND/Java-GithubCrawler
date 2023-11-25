package com.yasharhnd.github_crawler.datastore.controller;

import com.yasharhnd.github_crawler.commons.dto.ReqSearchResultsDto;
import com.yasharhnd.github_crawler.datastore.model.entity.SearchResult;
import com.yasharhnd.github_crawler.datastore.model.repository.SearchResultRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/search-results")
public class SearchResultsController {

    private final SearchResultRepository searchResultRepository;

    @PostMapping
    public ResponseEntity<Page<Object>> searchResults(@Valid @RequestBody final ReqSearchResultsDto dto, @RequestParam final Integer page) {
        final var pageRequest = PageRequest.of(page, 5);
        final var searchResults = searchResultRepository.findByTypeAndParameters(dto.getType(), dto.getCriteria(), pageRequest);
        return ResponseEntity.ok(new PageImpl<>(
            searchResults.stream().map(SearchResult::getFoundEntity).collect(Collectors.toList()),
            pageRequest,
            searchResults.getTotalElements()
        ));
    }

}
