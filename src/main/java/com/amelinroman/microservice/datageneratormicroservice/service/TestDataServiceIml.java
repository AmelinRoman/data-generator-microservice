package com.amelinroman.microservice.datageneratormicroservice.service;

import com.amelinroman.microservice.datageneratormicroservice.model.Data;
import com.amelinroman.microservice.datageneratormicroservice.model.test.DataTestOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TestDataServiceIml implements TestDataService {


    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final KafkaDataService kafkaDataService;

    @Override
    public void sendMessage(DataTestOptions dataTestOptions) {
        if (dataTestOptions.getMeasurementTypes().length > 0) {
            executorService.scheduleAtFixedRate(
                    () -> {
                        Data data = new Data();
                        data.setSensorId((long) getRandomNumber(1, 10));
                        data.setMeasurement(getRandomNumber(15, 100));
                        data.setMeasurementType(getRandomMeasurementType(dataTestOptions.getMeasurementTypes()));
                        data.setTimestamp(LocalDateTime.now());

                        kafkaDataService.send(data);
                    },
                    0,
                    dataTestOptions.getDelayInSeconds(),
                    TimeUnit.SECONDS);
        }
    }

    private double getRandomNumber(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }

    private Data.MeasurementType getRandomMeasurementType(Data.MeasurementType[] types) {
        int randomTypeId = (int) (Math.random() * types.length);

        return types[randomTypeId];
    }
}
