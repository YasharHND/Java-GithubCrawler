package com.yasharhnd.github_crawler.api.client;

import com.yasharhnd.github_crawler.commons.dto.ReqSearchResultsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "datastore", url = "${service.datastore.url}")
public interface DatastoreClient {

    @RequestMapping(method = RequestMethod.POST, value = "/search-results", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Page<Object> searchResults(@RequestBody ReqSearchResultsDto dto, @RequestParam Integer page);

}
