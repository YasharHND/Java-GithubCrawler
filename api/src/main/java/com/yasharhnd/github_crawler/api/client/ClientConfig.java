package com.yasharhnd.github_crawler.api.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ClientConfig {

    @Bean
    public RestTemplate datastoreMsClient(@Value("${internal.datastoreMs.url}") final String baseUrl) {
        final var client = new RestTemplate();
        client.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
        return client;
    }

}
