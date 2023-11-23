package com.yasharhnd.github_crawler.commons.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Bean
    public NewTopic topicSearchRepoCommand() {
        return new NewTopic(MessagingConstants.TOPIC_SEARCH_REPOS_COMMAND, 1, (short) 1);
    }

    @Bean
    public NewTopic topicTopicSearchReposResult() {
        return new NewTopic(MessagingConstants.TOPIC_SEARCH_REPOS_RESULT, 1, (short) 1);
    }

}
