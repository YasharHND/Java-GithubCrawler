package com.yasharhnd.github_crawler.github_graphql_client.client;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;

public final class GitHubException extends Exception {

    @Accessors(fluent = true)
    @Getter(AccessLevel.PUBLIC)
    private static final GitHubException throwable401 = new GitHubException(401);

    @Accessors(fluent = true)
    @Getter(AccessLevel.PUBLIC)
    private static final GitHubException throwable403 = new GitHubException(403);

    @Getter(AccessLevel.PUBLIC)
    private final Integer statusCode;

    private GitHubException(final Integer statusCode, final String message) {
        super(message);
        this.statusCode = statusCode;
    }

    private GitHubException(final Integer statusCode) {
        this(statusCode, null);
    }

    static GitHubException build500(final String message) {
        return new GitHubException(500, message);
    }

}
