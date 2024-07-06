package ru_hogwarts_school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Value("${server.port}")
    private String serverPort;

    Logger logger = LoggerFactory.getLogger(InfoService.class);

    public String getServerPort() {
        logger.info("Was invoked method for get server port");
        return serverPort;
    }
}
