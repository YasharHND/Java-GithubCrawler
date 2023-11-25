package com.yasharhnd.github_crawler.commons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yasharhnd.github_crawler.commons.enumeration.SearchType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
public class ReqSearchResultsDto {

    @NotNull
    @JsonProperty("type")
    private final SearchType type;

    @NotEmpty
    @JsonProperty("criteria")
    private final Map<String, String> criteria;

}
