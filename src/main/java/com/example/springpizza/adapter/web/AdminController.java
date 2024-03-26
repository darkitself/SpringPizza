package com.example.springpizza.adapter.web;

import com.example.springpizza.adapter.web.dto.request.CreateDishRequest;
import com.example.springpizza.adapter.web.dto.response.DishResponse;
import com.example.springpizza.service.AdminService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping(value = "/admin/pizza/v1", produces = APPLICATION_JSON_VALUE)
public class AdminController {

    AdminService adminService;

    @PostMapping(value = "/dish", consumes = APPLICATION_JSON_VALUE)
    public DishResponse createDish(@Valid @RequestBody CreateDishRequest orderRequest) {
        return adminService.createDish(orderRequest);
    }

    @DeleteMapping("/dish/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable Long dishId) {
        adminService.removeDish(dishId);
    }

    @GetMapping("/dish/{dishId}")
    public DishResponse getDish(@PathVariable Long dishId) {
        return adminService.getDish(dishId);
    }
}
