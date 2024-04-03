package com.example.springpizza.adapter.eventlistener;

import com.example.springpizza.domain.order.event.OrderCreatedEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Log4j2
@Service
public class OrderCreatedEventListener {

    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleOrderCreatedEvent(OrderCreatedEvent event) {
        //todo: отправлять нотификацию в другой сервис

        log.info("Processed event 'OrderCreatedEvent'. Order id = {}", event.getOrderEntity().getId());
    }
}