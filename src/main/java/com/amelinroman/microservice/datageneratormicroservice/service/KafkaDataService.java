package com.amelinroman.microservice.datageneratormicroservice.service;

import com.amelinroman.microservice.datageneratormicroservice.model.Data;

/**
 * Интерфейс для сервиса, отвечающего за отправку данных с использованием Apache Kafka.
 * <p>
 * Реализация этого интерфейса будет публиковать данные {@link Data} в топик Kafka для
 * дальнейшего использования последователями (например, другими сервисами или клиентами).
 *
 * @see Data
 */
public interface KafkaDataService {

    /**
     * Отправляет данные {@link Data} в топик Kafka.
     *
     * @param data Данные {@link Data} для отправки.
     */
    void send(Data data);
}
