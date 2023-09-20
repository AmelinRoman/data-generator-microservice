package com.amelinroman.microservice.datageneratormicroservice.service;

import com.amelinroman.microservice.datageneratormicroservice.model.Data;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

/**
 * Реализация интерфейса {@link KafkaDataService} для отправки данных с использованием Apache Kafka.
 * <p>
 * Этот сервис использует {@link KafkaSender} для отправки данных {@link Data} на соответствующий топик Kafka.
 *
 * @see KafkaDataService
 * @see Data
 */
@Service
@RequiredArgsConstructor
public class KafkaDataServiceIml implements KafkaDataService {

    /**
     * Инстанс {@link KafkaSender} для отправки данных в Kafka.
     */
    private final KafkaSender<String, Object> kafkaSender;

    /**
     * {@inheritDoc}
     * <p>
     * В данной реализации, метод отправляет данные {@link Data} в соответствующий топик
     * Kafka на основе значения поля {@code measurementType} {(@link Data#getMeasurementType()}}.
     * <p>
     * Используется {@link KafkaSender#send(Publisher)} (reactor.core.publisher.Mono)} для отправки данных.
     */
    @Override
    public void send(Data data) {
        String topic = switch (data.getMeasurementType()) {
            case TEMPERATURE -> "data-temperature";
            case VOLTAGE -> "data-voltage";
            case POWER -> "data-power";
        };
        kafkaSender.send(
                        Mono.just(
                                SenderRecord.create(
                                        topic,
                                        0,
                                        System.currentTimeMillis(),
                                        String.valueOf(data.hashCode()),
                                        data,
                                        null
                                )
                        )
                )
                .subscribe();
    }
}
