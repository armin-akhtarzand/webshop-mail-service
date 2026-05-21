package se.iths.armin.webshopmailservice.service;

import org.springframework.stereotype.Service;
import se.iths.armin.webshopmailservice.message.OrderConfirmationMessage;

@Service
public class MailService {

    public void sendOrderConfirmation(OrderConfirmationMessage message) {
        System.out.println("Sending email to: " + message.getCustomerEmail());
    }
}

