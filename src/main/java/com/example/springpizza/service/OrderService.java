package com.example.springpizza.service;

import com.example.springpizza.adapter.eventlistener.event.EmailSendEvent;
import com.example.springpizza.adapter.repository.OrderRepository;
import com.example.springpizza.adapter.web.dto.request.CreateOrderRequest;
import com.example.springpizza.adapter.web.dto.response.OrderResponse;
import com.example.springpizza.adapter.web.errors.MessageException;
import com.example.springpizza.adapter.web.errors.NotFoundException;
import com.example.springpizza.domain.audit.Auditable;
import com.example.springpizza.domain.order.OrderEntity;
import com.example.springpizza.domain.order.OrderStatus;
import com.example.springpizza.domain.user.UserEntity;
import com.example.springpizza.service.factory.OrderFactory;
import com.example.springpizza.service.mapper.OrderMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OrderService {

    private static final String ORDER_CREATED = "pizza.order.create";

    OrderRepository orderRepository;

    OrderMapper orderMapper;

    OrderFactory orderFactory;

    ApplicationEventPublisher applicationEventPublisher;

    MessageSourceAccessor messageSourceAccessor;

    OrderMessageService orderMessageService;


    @Auditable(type = "order_creation")
    public OrderResponse createOrder(UserEntity user, CreateOrderRequest orderRequest) {
        OrderEntity.OrderContext cntx = orderFactory.createContext(orderRequest);
        OrderEntity savedOrder = orderRepository.save(new OrderEntity(cntx, user));
        applicationEventPublisher.publishEvent(new EmailSendEvent(buildPayload()));
        return orderMapper.entityToResponse(savedOrder);
    }

    private String buildPayload() {
        return ORDER_CREATED;
    }

    @Auditable(type = "order_get")
    @Cacheable(cacheNames = "orders", key = "#orderId")
    public OrderResponse getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::entityToResponse)
                .orElseThrow(() -> new NotFoundException(orderId));
    }

    @CacheEvict(cacheNames = "orders", key = "#orderId")
    public void removeOrder(Long orderId) {
        var response = orderMessageService.sendOrderCancel(orderId);
        if (!response.result()) {
            throw new MessageException(response.message());
        }
        orderRepository.deleteById(orderId);
    }

    public void updateOrderStatus(Long orderId, OrderStatus status) {
        var orderOpt = orderRepository.findById(orderId);
        orderOpt.ifPresent(order -> {
            order.updateStatus(status);
            orderRepository.save(order);
        });
    }
}
