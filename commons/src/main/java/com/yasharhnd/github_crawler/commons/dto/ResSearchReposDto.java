package com.yasharhnd.github_crawler.commons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
public class ResSearchReposDto {

    @JsonProperty("forParameters")
    private final Map<String, String> forParameters;

    @JsonProperty("repo")
    private final RepoDto repo;

}
