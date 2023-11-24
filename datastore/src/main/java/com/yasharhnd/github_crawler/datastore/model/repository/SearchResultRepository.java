package com.yasharhnd.github_crawler.datastore.model.repository;

import com.yasharhnd.github_crawler.datastore.model.entity.SearchResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SearchResultRepository extends MongoRepository<SearchResult, String> {

}
