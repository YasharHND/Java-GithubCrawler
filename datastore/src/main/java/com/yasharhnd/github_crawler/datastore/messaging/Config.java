package com.yasharhnd.github_crawler.datastore.messaging;

import com.yasharhnd.github_crawler.commons.dto.CmdSearchReposDto;
import com.yasharhnd.github_crawler.commons.messaging.MessagingConstants;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@EnableKafka
@Configuration
@AllArgsConstructor
public class Config {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CmdSearchReposDto> containerFactory(@Value("${spring.kafka.producer.bootstrap-servers}") final String bootstrapServers) {
        final var listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, CmdSearchReposDto>();
        listenerContainerFactory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(Map.of(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG, MessagingConstants.GROUP_SEARCH_REPOS,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
            JsonDeserializer.TYPE_MAPPINGS, "resSearchReposDto:com.yasharhnd.github_crawler.commons.dto.ResSearchReposDto")));
        return listenerContainerFactory;
    }

}
