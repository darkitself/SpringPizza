package com.example.springpizza.service.deliveryinfo;

import com.example.springpizza.service.message.DeliveryResponse;

public interface DeliveryInfoServiceApi {

    DeliveryResponse getDeliveryInfo(Long orderId);
}
