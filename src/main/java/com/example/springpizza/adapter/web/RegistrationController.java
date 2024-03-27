package com.example.springpizza.adapter.web;

import com.example.springpizza.adapter.web.dto.request.RegistrationRequest;
import com.example.springpizza.service.RegistrationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/public/pizza/v1", produces = APPLICATION_JSON_VALUE)
public class RegistrationController {

    RegistrationService registrationService;

    @PostMapping(value = "/registration", consumes = APPLICATION_JSON_VALUE)
    public void registration(@RequestBody RegistrationRequest registrationRequest) {
        registrationService.registrateUser(registrationRequest);
    }
}
