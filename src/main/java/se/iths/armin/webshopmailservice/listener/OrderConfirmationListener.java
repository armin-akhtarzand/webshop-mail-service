package se.iths.armin.webshopmailservice.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import se.iths.armin.webshopmailservice.config.RabbitMQConfig;
import se.iths.armin.webshopmailservice.message.OrderConfirmationMessage;
import se.iths.armin.webshopmailservice.service.OrderConfirmationService;

@Component
public class OrderConfirmationListener {
    private final OrderConfirmationService orderConfirmationService;

    public OrderConfirmationListener(OrderConfirmationService orderConfirmationService) {
        this.orderConfirmationService = orderConfirmationService;
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_CONFIRMATION_QUEUE)
    public void receiveOrderConfirmation(OrderConfirmationMessage message) {
        orderConfirmationService.sendOrderConfirmation(message);
    }
}
