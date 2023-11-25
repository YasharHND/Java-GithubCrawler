package com.yasharhnd.github_crawler.datastore.model.entity;

import com.yasharhnd.github_crawler.commons.enumeration.SearchType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Getter
@AllArgsConstructor
@Document(collection = "searchResults")
public class SearchResult {

    @Id
    private final String id;

    private final SearchType type;
    private final Map<String, String> parameters;
    private final Object foundEntity;

}
