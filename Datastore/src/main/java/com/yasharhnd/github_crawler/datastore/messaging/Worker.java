package com.yasharhnd.github_crawler.datastore.messaging;

import com.yasharhnd.github_crawler.commons.dto.ResSearchReposDto;
import com.yasharhnd.github_crawler.commons.messaging.MessagingConstants;
import com.yasharhnd.github_crawler.datastore.mapper.SearchResultMapper;
import com.yasharhnd.github_crawler.datastore.model.repository.SearchResultRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class Worker {

    private final SearchResultMapper searchResultMapper;
    private final SearchResultRepository searchResultRepository;

    @KafkaListener(groupId = MessagingConstants.GROUP_SEARCH_REPOS, topics = MessagingConstants.TOPIC_SEARCH_REPOS_RESULT, containerFactory = "containerFactory")
    public void handleRepo(@Payload ResSearchReposDto dto) {
        final var searchResult = searchResultRepository.save(searchResultMapper.toRepoSearchResult(dto));
        log.info("Saved SearchResult(id:{})", searchResult.getId());
    }

}
