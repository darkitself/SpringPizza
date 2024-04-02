package com.example.springpizza.adapter.eventlistener;

import com.example.springpizza.adapter.eventlistener.event.AsyncCreateOrderEvent;
import com.example.springpizza.adapter.repository.OrderRepository;
import com.example.springpizza.domain.order.OrderEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AsyncCreateOrderEventListener {

    OrderRepository orderRepository;

    @Async
    @EventListener
    public void onApplicationEvent(AsyncCreateOrderEvent event) throws InterruptedException {

        //todo: Получение информации из другого сервиса

        Thread.sleep(5000); //симулируем тяжелую работу
        orderRepository.save(new OrderEntity(event.context(), event.userEntity()));
    }
}
