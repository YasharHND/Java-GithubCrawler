package com.yasharhnd.github_crawler.commons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
public class CmdSearchReposDto {

    @JsonProperty("parameters")
    private final Map<String, String> parameters;

    @JsonProperty("count")
    private final Integer count;

}
