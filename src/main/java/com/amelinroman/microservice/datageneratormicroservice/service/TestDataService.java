package com.amelinroman.microservice.datageneratormicroservice.service;

import com.amelinroman.microservice.datageneratormicroservice.model.test.DataTestOptions;

public interface TestDataService {

    void sendMessage(DataTestOptions dataTestOptions);
}
