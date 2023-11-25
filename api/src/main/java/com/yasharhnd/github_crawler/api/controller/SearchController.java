package com.yasharhnd.github_crawler.api.controller;

import com.yasharhnd.github_crawler.api.dto.ReqSearchReposDto;
import com.yasharhnd.github_crawler.api.mapper.SearchMapper;
import com.yasharhnd.github_crawler.commons.dto.CmdSearchReposDto;
import com.yasharhnd.github_crawler.commons.messaging.MessagingConstants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchMapper searchMapper;
    private final KafkaTemplate<String, CmdSearchReposDto> searchReposCmdTemplate;

    @PostMapping("/repos")
    public ResponseEntity<Void> search(@Valid @RequestBody final ReqSearchReposDto dto) {
        searchReposCmdTemplate.send(MessagingConstants.TOPIC_SEARCH_REPOS_COMMAND, searchMapper.toCmdSearchReposDto(dto));
        return ResponseEntity.noContent().build();
    }

}
