package com.example.springpizza.service.deliveryinfo;

import com.example.springpizza.service.message.DeliveryResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmulatorDeliveryInfoService implements DeliveryInfoServiceApi {

    private final Integer range;

    @Override
    public DeliveryResponse getDeliveryInfo(Long orderId) {
        return DeliveryResponse.builder()
                .orderId(orderId)
                .startDelivery(LocalDateTime.now().minusHours(range))
                .endDelivery(LocalDateTime.now().plusHours(range))
                .build();
    }
}
