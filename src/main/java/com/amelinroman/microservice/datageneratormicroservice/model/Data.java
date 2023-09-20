package com.amelinroman.microservice.datageneratormicroservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Класс Data представляет данные, полученные от датчика, и хранит информацию о
 * идентификаторе датчика, времени измерения, значении измерения и типе измерительной информации.
 * <p>
 * Поддерживаемые типы измерительной информации находятся во вложенном перечислении {@link MeasurementType}.
 */
@NoArgsConstructor
@Getter
@Setter
public class Data {

    /**
     * Идентификационный номер датчика.
     */
    private Long sensorId;

    /**
     * Временная метка измерения.
     */
    private LocalDateTime timestamp;

    /**
     * Значение измерений, полученных от датчика.
     */
    private double measurement;

    /**
     * Тип измерительной информации (например, температура, напряжение, мощность).
     */
    private MeasurementType measurementType;

    /**
     * Перечисление доступных типов измерительной информации.
     */
    public enum MeasurementType {
        TEMPERATURE,
        VOLTAGE,
        POWER
    }
}
