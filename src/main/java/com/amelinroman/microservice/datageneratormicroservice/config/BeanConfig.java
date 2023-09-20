package com.amelinroman.microservice.datageneratormicroservice.config;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Класс конфигурации, содержащий определение бина для создания экземпляра объекта XML.
 * Этот бин используется для чтения файла конфигурации Kafka producer.
 */
@Configuration
public class BeanConfig {

    /**
     * Создает и возвращает объект типа XML, представляющий документ XML,
     * прочитанный из файла конфигурации и расположенный по пути
     * "src/main/resources/kafka/producer.xml".
     * @return объект XML, представляющий документ XML конфигурации Kafka producer
     */

    @SneakyThrows
    @Bean
    public XML producerXml() {
        return new XMLDocument(
                new File("src/main/resources/kafka/producer.xml")
        );
    }
}
