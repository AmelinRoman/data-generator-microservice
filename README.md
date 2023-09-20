# Микросервис генератор данных об измениях на датчиках


Это приложение создает данные и отправляет их в [службу обработки данных](https://github.com/AmelinRoman/data-analyser-microservice) с помощью Apache Kafka.

## Переменные окружения

Перед запуском приложения убедитесь, что вы создали файл .env со следующими переменными:


`KAFKA_BOOTSTRAP_SERVERS=localhost:9092`


## Запуск приложения

Для запуска приложения выполните следующие шаги:


1. Установите зависимости: npm install.


2. Запустите приложение: npm start.


3. Приложение будет запущено на порту 8081.


## Api

Приложение имеет две конечные точки:

### POST /api/v1/данные/отправить

Отправляет данные в Kafka. Пример JSON:

```json{
  "sensorId": 1,
  "timestamp": "2023-09-12T12:10:05",
  "measurement": 15.5,
  "measurementType": "TEMPERATURE"
}
```


### POST /api/v1/data/test/send


Отправляет тестовые данные в Kafka через некоторый интервал времени. Пример JSON:

```json{
  "delayInSeconds": 3,
  "measurementTypes": [
    "POWER",
    "VOLTAGE",
    "TEMPERATURE"
  ]
}
```

