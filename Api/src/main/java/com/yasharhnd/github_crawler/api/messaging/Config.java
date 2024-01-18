package com.yasharhnd.github_crawler.api.messaging;

import com.yasharhnd.github_crawler.commons.dto.CmdSearchReposDto;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
@AllArgsConstructor
public class Config {

    @Bean
    public KafkaTemplate<String, CmdSearchReposDto> searchReposCmdTemplate(@Value("${spring.kafka.bootstrap-servers}") final String bootstrapServers) {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
            JsonSerializer.TYPE_MAPPINGS, "cmdSearchRepos:com.yasharhnd.github_crawler.commons.dto.CmdSearchReposDto")));
    }

}
