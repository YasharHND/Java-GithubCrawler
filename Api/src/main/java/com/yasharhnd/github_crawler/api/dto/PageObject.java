package com.yasharhnd.github_crawler.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class PageObject extends PageImpl<Object> {

    @JsonCreator
    public PageObject(@JsonProperty("content") final List<Object> content,
                      @JsonProperty("number") final Integer number,
                      @JsonProperty("size") final Integer size,
                      @JsonProperty("totalElements") final Long totalElements,
                      @JsonProperty("pageable") final JsonNode ignored) {
        super(content, PageRequest.of(number, size), totalElements);
    }

}
