package com.yasharhnd.github_crawler.datastore.model.repository;

import com.yasharhnd.github_crawler.commons.enumeration.SearchType;
import com.yasharhnd.github_crawler.datastore.model.entity.SearchResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Map;

public interface SearchResultRepository extends MongoRepository<SearchResult, String> {

    @Query("{'parameters.language': '?0', 'foundEntity.name': {$regex: ?1, $options: i}}")
    Page<SearchResult> findByLanguageAndNameStartsWith(String language, String nameStartsWith, Pageable pageable);

    @SuppressWarnings("unused")
    Page<SearchResult> findByTypeAndParameters(SearchType type, Map<String, String> parameters, Pageable pageable);

}
