package com.yasharhnd.github_crawler.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
public class ReqSearchReposDto {

    @NotNull
    @NotEmpty
    @JsonProperty("parameters")
    private final Map<String, String> parameters;

    @NotNull
    @Range(min = 1, max = 100)
    @JsonProperty("count")
    private final Integer count;

}
