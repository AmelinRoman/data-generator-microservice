package com.amelinroman.microservice.datageneratormicroservice.web.dto;

import com.amelinroman.microservice.datageneratormicroservice.model.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс DataDto представляет собой объект передачи данных (DTO) для данными измерений.
 * Используется для сериализации/десериализации данных измерений при обработке запросов и ответов в контроллере.
 */
@NoArgsConstructor
@Getter
@Setter
public class DataDto {

    /**
     * Идентификатор датчика.
     */
    private Long sensorId;

    /**
     * Временная метка измерения в формате 'yyyy-MM-dd'T'HH:mm:ss'.
     */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * Значение измерения.
     */
    private double measurement;

    /**
     * Тип измерения (перечисления из класса Data.MeasurementType).
     */
    private Data.MeasurementType measurementType;

}
