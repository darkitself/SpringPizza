package com.example.springpizza.service;

import com.example.springpizza.adapter.repository.DishRepository;
import com.example.springpizza.adapter.web.dto.request.CreateDishRequest;
import com.example.springpizza.adapter.web.dto.response.DishResponse;
import com.example.springpizza.adapter.web.errors.NotFoundException;
import com.example.springpizza.domain.DishEntity;
import com.example.springpizza.service.mapper.DishMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AdminService {

    DishMapper dishMapper;
    DishRepository dishRepository;

    public DishResponse createDish(CreateDishRequest dishRequest) {
        DishEntity savedEntity = dishRepository.save(DishEntity.createDishFrom(dishRequest));
        return dishMapper.entityToResponse(savedEntity);
    }

    public DishResponse getDish(Long dishId) {
        return dishRepository.findById(dishId)
                .map(dishMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(dishId));
    }

    public void removeDish(Long dishId) {
        dishRepository.deleteById(dishId);
    }
}
