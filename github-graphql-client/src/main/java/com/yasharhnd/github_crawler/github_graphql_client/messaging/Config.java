package com.yasharhnd.github_crawler.github_graphql_client.messaging;

import com.yasharhnd.github_crawler.commons.dto.CmdSearchReposDto;
import com.yasharhnd.github_crawler.commons.dto.ResSearchReposDto;
import com.yasharhnd.github_crawler.commons.messaging.MessagingConstants;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@EnableKafka
@Configuration
@AllArgsConstructor
public class Config {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private final String bootstrapServers;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CmdSearchReposDto> containerFactory() {
        final var listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<String, CmdSearchReposDto>();
        listenerContainerFactory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(Map.of(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG, MessagingConstants.GROUP_SEARCH_REPOS,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
            JsonDeserializer.TYPE_MAPPINGS, "cmdSearchRepos:com.yasharhnd.github_crawler.commons.dto.CmdSearchReposDto")));
        return listenerContainerFactory;
    }

    @Bean
    public KafkaTemplate<String, ResSearchReposDto> searchReposResTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(Map.of(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class,
            JsonSerializer.TYPE_MAPPINGS, "resSearchReposDto:com.yasharhnd.github_crawler.commons.dto.ResSearchReposDto")));
    }

}
