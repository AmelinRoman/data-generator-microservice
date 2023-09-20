package com.amelinroman.microservice.datageneratormicroservice.web.controller;

import com.amelinroman.microservice.datageneratormicroservice.model.Data;
import com.amelinroman.microservice.datageneratormicroservice.model.test.DataTestOptions;
import com.amelinroman.microservice.datageneratormicroservice.service.KafkaDataService;
import com.amelinroman.microservice.datageneratormicroservice.service.TestDataService;
import com.amelinroman.microservice.datageneratormicroservice.web.dto.DataDto;
import com.amelinroman.microservice.datageneratormicroservice.web.dto.test.DataTestOptionsDto;
import com.amelinroman.microservice.datageneratormicroservice.web.mapper.DataMapper;
import com.amelinroman.microservice.datageneratormicroservice.web.mapper.DataMapperTestOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Контроллер DataController предоставляет API для работы с данными измерений и их отправки в Kafka.
 */
@RestController
@RequestMapping("/api/v1/data")
@RequiredArgsConstructor
public class DataController {

    private final DataMapperTestOptions dataMapperTestOptions;
    private final TestDataService testDataService;
    private final KafkaDataService kafkaDataService;
    public final DataMapper mapper;


    /**
     * Метод send принимает входящий объект DataDto и отображает его на объект Data.
     * Затем отправляет объект Data для обработки в сервис KafkaDataService.
     *
     * @param dto объект DataDto, представляющий JSON-структуру данных измерений.
     */
    @PostMapping("/send")
    public void send(@RequestBody DataDto dto) {
        Data data = mapper.toEntity(dto);
        kafkaDataService.send(data);
    }

    /**
     * Метод testSend принимает входящий объект DataTestOptionsDto и отображает его на объект DataTestOptions.
     * Затем отправляет объект DataTestOptions для обработки в сервис TestDataService.
     * Этот метод предназначен для тестирования отправки сообщений.
     *
     * @param dataTestOptionsDto объект DataTestOptionsDto, представляющий JSON-структуру опций для тестовых данных измерений.
     */
    @PostMapping("/test/send")
    public void testSend(@RequestBody DataTestOptionsDto dataTestOptionsDto) {
        DataTestOptions data = dataMapperTestOptions.toEntity(dataTestOptionsDto);
        testDataService.sendMessage(data);
    }
}
