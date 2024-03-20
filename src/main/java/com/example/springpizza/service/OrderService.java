package com.example.springpizza.service;

import com.example.springpizza.adapter.repository.DishRepository;
import com.example.springpizza.adapter.repository.OrderRepository;
import com.example.springpizza.adapter.web.dto.CreateOrderRequest;
import com.example.springpizza.adapter.web.errors.NotFoundException;
import com.example.springpizza.domain.DishEntity;
import com.example.springpizza.domain.OrderEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderService {

    OrderRepository orderRepository;

    DishRepository dishRepository;

    public OrderEntity createOrder(CreateOrderRequest orderRequest) {
        List<DishEntity> dishes = dishRepository.findAllById(orderRequest.dishesAndCount().keySet());
        OrderEntity order = OrderEntity.createOrderFrom(dishes, orderRequest);
        return orderRepository.save(order);
    }

    public OrderEntity getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(orderId));
    }

    public void removeOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
