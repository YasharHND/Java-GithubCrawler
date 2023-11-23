package com.yasharhnd.github_crawler.github_graphql_client.messaging;

import com.github.graphql.SearchRepositoriesQuery;
import com.yasharhnd.github_crawler.commons.dto.CmdSearchReposDto;
import com.yasharhnd.github_crawler.commons.dto.ResSearchReposDto;
import com.yasharhnd.github_crawler.commons.messaging.MessagingConstants;
import com.yasharhnd.github_crawler.github_graphql_client.client.GitHubException;
import com.yasharhnd.github_crawler.github_graphql_client.client.GraphQlClient;
import com.yasharhnd.github_crawler.github_graphql_client.mapper.RepoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class Worker {

    private final GraphQlClient client;
    private final Executor threadPoolExecutor;
    private final RepoMapper repoMapper;
    private final KafkaTemplate<String, ResSearchReposDto> searchReposResTemplate;

    @KafkaListener(groupId = MessagingConstants.GROUP_SEARCH_REPOS, topics = MessagingConstants.TOPIC_SEARCH_REPOS_COMMAND, containerFactory = "containerFactory")
    public void searchRepos(@Payload final CmdSearchReposDto dto) {

        final var criteria = dto.getParameters().entrySet()
            .stream()
            .map(entry -> String.format("%s:%s", entry.getKey(), entry.getValue()))
            .collect(Collectors.joining(" "));

        log.info("Received message to search up to {} repos for criteria '{}'", dto.getCount(), criteria);

        try {
            client.query(SearchRepositoriesQuery.builder()
                .query(criteria)
                .first(dto.getCount())
                .build()).repositories.edges.forEach(repo -> handleRepo(dto.getParameters(), repo));
        } catch (final GitHubException e) {
            log.warn("Encountered GitHub exception, statusCode: {}, message: {}", e.getStatusCode(), e.getMessage());
        }
    }

    private void handleRepo(final Map<String, String> parameters, final SearchRepositoriesQuery.Edge repo) {
        threadPoolExecutor.execute(() -> searchReposResTemplate.send(MessagingConstants.TOPIC_SEARCH_REPOS_RESULT, ResSearchReposDto.builder()
            .forParameters(parameters)
            .repo(repoMapper.toRepoDto(repo.node.publicRepository))
            .build()));
    }

}
