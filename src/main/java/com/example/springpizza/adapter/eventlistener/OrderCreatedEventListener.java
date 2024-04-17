package com.example.springpizza.adapter.eventlistener;

import com.example.springpizza.domain.order.event.OrderCreatedEvent;
import com.example.springpizza.service.OrderMessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Log4j2
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OrderCreatedEventListener {

    OrderMessageService orderMessageService;

    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("Processed event 'OrderCreatedEvent'. Order id = {}", event.getOrderEntity().getId());
        orderMessageService.sendOrderCreate(event.getOrderEntity());
    }
}
