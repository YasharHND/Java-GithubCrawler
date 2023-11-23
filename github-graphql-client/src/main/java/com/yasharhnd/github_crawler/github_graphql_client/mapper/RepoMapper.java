package com.yasharhnd.github_crawler.github_graphql_client.mapper;

import com.github.graphql.fragment.PublicRepository;
import com.yasharhnd.github_crawler.commons.dto.RepoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RepoMapper {

    @Mapping(target = "ownerLogin", source = "owner.login")
    @Mapping(target = "descriptionHTML", expression = "java(String.valueOf(source.descriptionHTML))")
    @Mapping(target = "homepageUrl", expression = "java(String.valueOf(source.homepageUrl))")
    RepoDto toRepoDto(PublicRepository source);

}
