package com.yasharhnd.github_crawler.commons.messaging;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessagingConstants {

    public static final String GROUP_SEARCH_REPOS = "com.yasharhnd.github-crawler.GROUP.SEARCH_REPOS";

    public static final String TOPIC_SEARCH_REPOS_COMMAND = "com.yasharhnd.github-crawler.TOPIC.SEARCH_REPOS_COMMAND";
    public static final String TOPIC_SEARCH_REPOS_RESULT = "com.yasharhnd.github-crawler.TOPIC.SEARCH_REPOS_RESULT";

}
