package com.yasharhnd.github_crawler.datastore.mapper;

import com.yasharhnd.github_crawler.commons.dto.ResSearchReposDto;
import com.yasharhnd.github_crawler.datastore.model.entity.SearchResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchResultMapper {

    @Mapping(target = "type", constant = "REPO")
    @Mapping(target = "parameters", source = "forParameters")
    @Mapping(target = "foundEntity", source = "repo")
    SearchResult toRepoSearchResult(ResSearchReposDto source);

}
