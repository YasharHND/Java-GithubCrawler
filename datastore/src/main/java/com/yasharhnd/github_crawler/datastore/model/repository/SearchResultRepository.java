package com.yasharhnd.github_crawler.datastore.model.repository;

import com.yasharhnd.github_crawler.datastore.model.entity.SearchResult;
import com.yasharhnd.github_crawler.commons.enumeration.SearchType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

public interface SearchResultRepository extends MongoRepository<SearchResult, String> {

    Page<SearchResult> findByTypeAndParameters(SearchType type, Map<String, String> parameters, Pageable pageable);

}
