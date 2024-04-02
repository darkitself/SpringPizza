package com.example.springpizza.service.emailsend;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@ConditionalOnProperty(prefix = "pizza.email", name = "enable", havingValue = "false")
public class EmailSendServiceStub implements EmailSendService {

    @Override
    public void send(List<String> recipients, String payload) {
        log.info("Email stub was send. Payload = {}", payload);
    }
}
