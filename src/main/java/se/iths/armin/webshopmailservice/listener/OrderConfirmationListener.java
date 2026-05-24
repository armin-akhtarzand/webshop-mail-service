package se.iths.armin.webshopmailservice.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import se.iths.armin.webshopmailservice.config.RabbitMQConfig;
import se.iths.armin.webshopmailservice.message.OrderConfirmationMessage;
import se.iths.armin.webshopmailservice.service.MailService;

@Component
public class OrderConfirmationListener {
    private final MailService mailService;

    public OrderConfirmationListener(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = RabbitMQConfig.ORDER_CONFIRMATION_QUEUE)
    public void receiveOrderConfirmation(OrderConfirmationMessage message) {
        mailService.sendOrderConfirmation(message);
    }
}
