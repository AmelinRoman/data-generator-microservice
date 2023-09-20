package com.amelinroman.microservice.datageneratormicroservice.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация Kafka для приложения.
 * Этот класс создает конфигурацию подключения и объекты {@link NewTopic} для работы с темами Kafka.
 * Также содержит конфигурацию для {@link KafkaSender}, который используется для отправки сообщений.
 */
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    /**
     * Адрес серверов Kafka
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    /**
     * Настройки XML
     */
    private final XML settings;

    /**
     * Создает объект {@link NewTopic} для темы "data-temperature".
     * @return объект {@link NewTopic}, который описывает тему "data-temperature".
     */
    @Bean
    public NewTopic temperatureTopic() {
        return TopicBuilder.name("data-temperature")
                .partitions(5)
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(7).toMillis())
                ).build();
    }

    /**
     * Создает объект {@link NewTopic} для темы "data-voltage".
     * @return объект {@link NewTopic}, который описывает тему "data-voltage".
     */
    @Bean
    public NewTopic voltageTopic() {
        return TopicBuilder.name("data-voltage")
                .partitions(5)
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(7).toMillis())
                ).build();
    }

    /**
     * Создает объект {@link NewTopic} для темы "data-power".
     * @return объект {@link NewTopic}, который описывает тему "data-power".
     */
    @Bean
    public NewTopic powerTopic() {
        return TopicBuilder.name("data-power")
                .partitions(5)
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(7).toMillis())
                ).build();
    }

    /**
     * Создает объект {@link SenderOptions} с настройками сериализации ключей и значений.
     * Настройки берутся из файла конфигурации XML.
     * @return объект {@link SenderOptions} с настройками для Kafka.
     */
    @Bean
    public SenderOptions<String, Object> senderOptions() {
        Map<String, Object> props = new HashMap<>(3);

        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                servers
        );

        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                new TextXPath(this.settings, "//keySerializer").toString()
        );

        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                new TextXPath(this.settings, "//valueSerializer").toString()
        );

        return SenderOptions.create(props);
    }

    /**
     * Создает объект {@link KafkaSender} с использованием настроек из {@link #senderOptions()}.
     * @return объект {@link KafkaSender} для отправки сообщений на серверы Kafka.
     */
    @Bean
    public KafkaSender<String, Object> kafkaSender() {
        return KafkaSender.create(senderOptions());
    }
}
