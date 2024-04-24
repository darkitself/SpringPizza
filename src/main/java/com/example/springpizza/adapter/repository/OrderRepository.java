package com.example.springpizza.adapter.repository;

import com.example.springpizza.domain.order.OrderEntity;
import com.example.springpizza.domain.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    void deleteAllByStatusAndUpdatedAtIsBefore(OrderStatus status, LocalDateTime updatedAt);
}
