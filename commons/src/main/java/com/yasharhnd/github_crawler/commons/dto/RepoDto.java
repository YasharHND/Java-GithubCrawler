package com.yasharhnd.github_crawler.commons.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor = @__(@JsonCreator))
public class RepoDto {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("nameWithOwner")
    private final String nameWithOwner;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("ownerLogin")
    private final String ownerLogin;

    @JsonProperty("description")
    private final String description;

    @JsonProperty("descriptionHTML")
    private final String descriptionHTML;

    @JsonProperty("homepageUrl")
    private final String homepageUrl;

    @JsonProperty("forkCount")
    private final Integer forkCount;

    @JsonProperty("forkingAllowed")
    private final Boolean forkingAllowed;

    @JsonProperty("isFork")
    private final Boolean isFork;

    @JsonProperty("isArchived")
    private final Boolean isArchived;

    @JsonProperty("isPrivate")
    private final Boolean isPrivate;

    @JsonProperty("isLocked")
    private final Boolean isLocked;

}
