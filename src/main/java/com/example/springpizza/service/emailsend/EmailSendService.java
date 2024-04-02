package com.example.springpizza.service.emailsend;

import java.util.List;

public interface EmailSendService {

    void send(List<String> recipients, String payload);
}
