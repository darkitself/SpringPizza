package com.example.springpizza.adapter.web.dto.request;

import com.example.springpizza.domain.user.UserRole;

import java.util.List;

public record RegistrationRequest(String login,
                                  String password,
                                  List<UserRole> roles) {
}
