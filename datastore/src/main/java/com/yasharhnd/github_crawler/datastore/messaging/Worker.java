package com.yasharhnd.github_crawler.datastore.messaging;

import com.yasharhnd.github_crawler.commons.dto.ResSearchReposDto;
import com.yasharhnd.github_crawler.commons.messaging.MessagingConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Worker {

    @KafkaListener(groupId = MessagingConstants.GROUP_SEARCH_REPOS, topics = MessagingConstants.TOPIC_SEARCH_REPOS_RESULT, containerFactory = "containerFactory")
    public void handleRepo(@Payload ResSearchReposDto dto) {
        log.info("Handling repo {}", dto);
    }

}
