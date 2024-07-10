package ru_hogwarts_school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class InfoService {

    @Value("${server.port}")
    private String serverPort;

    Logger logger = LoggerFactory.getLogger(InfoService.class);

    public String getServerPort() {
        logger.info("Was invoked method for get server port");
        return serverPort;
    }

    public Integer getSum() {
        logger.info("Создан эндпоинт с модифицированной логикой предложенного решения. " +
                "В результате модификаций эндпоинт возвращает значение за меньшее количество времени.");

        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
    }
}
