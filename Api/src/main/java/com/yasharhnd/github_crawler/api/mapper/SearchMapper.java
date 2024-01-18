package com.yasharhnd.github_crawler.api.mapper;

import com.yasharhnd.github_crawler.api.dto.ReqSearchReposDto;
import com.yasharhnd.github_crawler.commons.dto.CmdSearchReposDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SearchMapper {

    CmdSearchReposDto toCmdSearchReposDto(ReqSearchReposDto source);

}
