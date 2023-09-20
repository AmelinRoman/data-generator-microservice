package com.amelinroman.microservice.datageneratormicroservice.web.mapper;

import com.amelinroman.microservice.datageneratormicroservice.model.test.DataTestOptions;
import com.amelinroman.microservice.datageneratormicroservice.web.dto.test.DataTestOptionsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapperTestOptions extends Mappable<DataTestOptions, DataTestOptionsDto> {
}
