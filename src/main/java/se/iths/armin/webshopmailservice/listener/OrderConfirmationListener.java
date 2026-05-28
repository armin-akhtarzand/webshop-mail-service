package se.iths.armin.webshopmailservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import se.iths.armin.webshopmailservice.config.RabbitMQConfig;
import se.iths.armin.webshopmailservice.message.OrderConfirmationMessage;
import se.iths.armin.webshopmailservice.service.OrderConfirmationService;

@Component
public class OrderConfirmationListener {
    private final OrderConfirmationService orderConfirmationService;
    private final ObjectMapper objectMapper;

    public OrderConfirmationListener(OrderConfirmationService orderConfirmationService,
                                     ObjectMapper objectMapper) {
        this.orderConfirmationService = orderConfirmationService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_CONFIRMATION_QUEUE)
    public void receiveOrderConfirmation(byte[] payload) throws Exception {
        OrderConfirmationMessage message = objectMapper.readValue(payload, OrderConfirmationMessage.class);
        orderConfirmationService.sendOrderConfirmation(message);
    }
}
