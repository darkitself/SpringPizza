package com.example.springpizza.service;

import com.example.springpizza.adapter.repository.OrderRepository;
import com.example.springpizza.adapter.web.dto.request.CreateOrderRequest;
import com.example.springpizza.adapter.web.dto.response.OrderResponse;
import com.example.springpizza.adapter.web.errors.NotFoundException;
import com.example.springpizza.domain.audit.Auditable;
import com.example.springpizza.domain.order.OrderEntity;
import com.example.springpizza.domain.user.UserEntity;
import com.example.springpizza.service.factory.OrderFactory;
import com.example.springpizza.service.mapper.OrderMapper;
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
public class OrderService {

    OrderRepository orderRepository;

    OrderMapper orderMapper;

    OrderFactory orderFactory;

    @Auditable(type = "order_creation")
    public OrderResponse createOrder(UserEntity user, CreateOrderRequest orderRequest) {
        OrderEntity.OrderContext cntx = orderFactory.createContext(orderRequest);
        OrderEntity savedOrder = orderRepository.save(new OrderEntity(cntx, user));
        return orderMapper.entityToResponse(savedOrder);
    }

    @Auditable(type = "order_get")
    public OrderResponse getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(orderId));
    }

    public void removeOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
