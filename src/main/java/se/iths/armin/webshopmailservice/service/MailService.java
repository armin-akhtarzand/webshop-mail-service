package se.iths.armin.webshopmailservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import se.iths.armin.webshopmailservice.message.OrderConfirmationMessage;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOrderConfirmation(OrderConfirmationMessage message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(from);
        email.setTo(message.getCustomerEmail());
        email.setSubject("Order Confirmation");
        email.setText(buildEmailText(message));
        mailSender.send(email);
    }

    private String buildEmailText(OrderConfirmationMessage message) {
        StringBuilder sb = new StringBuilder();
        sb.append("Thank you for your order!\n");
        sb.append("Order date: ").append(message.getOrderDate()).append("\n\n");
        sb.append("Items:\n");
        message.getItems().forEach(item ->
                sb.append("- ").append(item.getName())
                        .append(" x").append(item.getQuantity())
                        .append(" = ").append(item.getPrice()).append("\n")
        );
        sb.append("\nTotal: ").append(message.getTotalPrice());
        return sb.toString();
    }
}