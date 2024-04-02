package com.example.springpizza.adapter.eventlistener;

import com.example.springpizza.adapter.eventlistener.event.EmailSendEvent;
import com.example.springpizza.config.properties.EmailConfigProperties;
import com.example.springpizza.service.emailsend.EmailSendService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmailSendEventListener {

    EmailSendService emailSendService;
    EmailConfigProperties emailConfigProperties;

    @SneakyThrows
    @Async
    @EventListener
    public void onApplicationEvent(EmailSendEvent event)  {

//        Thread.sleep(5000); //симулируем тяжелую работу

        emailSendService.send(emailConfigProperties.getRecipients(), event.payload());
    }
}
