package com.example.springpizza.service.deliveryinfo;

import com.example.springpizza.config.property.DeliveryOrderProperties;
import com.example.springpizza.service.message.DeliveryResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import static com.example.springpizza.common.Profile.PROD;

@Service
@Profile(PROD)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Log4j2
public class DeliveryInfoService implements DeliveryInfoServiceApi {

    DeliveryOrderProperties deliveryOrderProperties;

    @Override
    public DeliveryResponse getDeliveryInfo(Long orderId) {
        if (!deliveryOrderProperties.isEnable()) {
            log.info("Delivery order info is disabled");
            return null;
        }
        throw new NotImplementedException();
    }
}
