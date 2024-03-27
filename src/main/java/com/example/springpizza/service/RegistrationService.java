package com.example.springpizza.service;

import com.example.springpizza.adapter.repository.UserRepository;
import com.example.springpizza.adapter.web.dto.request.RegistrationRequest;
import com.example.springpizza.domain.user.UserEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegistrationService {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public void registrateUser(RegistrationRequest request) {
        UserEntity.Context context = new UserEntity.Context(
                request.login(),
                passwordEncoder.encode(request.password()),
                request.roles()
        );
        UserEntity newUser = UserEntity.from(context);
        userRepository.save(newUser);
    }
}
