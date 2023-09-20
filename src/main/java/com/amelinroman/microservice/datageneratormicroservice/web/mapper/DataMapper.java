package com.amelinroman.microservice.datageneratormicroservice.web.mapper;

import com.amelinroman.microservice.datageneratormicroservice.model.Data;
import com.amelinroman.microservice.datageneratormicroservice.web.dto.DataDto;
import org.mapstruct.Mapper;

/**
 * Реализация интерфейса Mappable для преобразования между объектами Data и DataDto.
 * Использует компонентную модель Spring для внедрения зависимостей.
 * <p>
 * Внедрение зависимостей позволяет автоматически сгенерировать реализацию данного интерфейса
 * с использованием библиотеки MapStruct.
 *
 * @see Mappable
 * @see Data
 * @see DataDto
 */
@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto> {

}
