package com.example.springpizza.service.scheduler;

import com.example.springpizza.adapter.repository.OrderRepository;
import com.example.springpizza.config.properties.OrderProperties;
import com.example.springpizza.domain.order.OrderStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@EnableAsync
@Log4j2
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderScheduler {

    OrderRepository orderRepository;
    OrderProperties orderProperties;

    @SchedulerLock(name = "DeleteOldProcessedOrders")
//    @Scheduled(fixedDelayString = "${pizza.order.job.delete.fixed-delay}")
//    @Async
//    @Scheduled(fixedRateString = "${pizza.order.job.delete.fixed-rate}")
    @Scheduled(cron = "${pizza.order.job.delete.cron}")
    @SneakyThrows
    public void expiredProcessedOrders() {

        log.info("Scheduler deleteOldProcessedOrders start");
//        Thread.sleep(15000);
        var lastLiveOrder = LocalDateTime.now().minusSeconds(orderProperties.getTtl().toSeconds());
        orderRepository.deleteAllByStatusAndUpdatedAtIsBefore(OrderStatus.CREATED, lastLiveOrder);

        log.info("Scheduler deleteOldProcessedOrders end");
    }
}
