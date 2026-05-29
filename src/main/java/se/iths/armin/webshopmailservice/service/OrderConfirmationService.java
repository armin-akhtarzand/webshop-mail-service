package se.iths.armin.webshopmailservice.service;

import org.springframework.stereotype.Service;
import se.iths.armin.mailservice.MailService;
import se.iths.armin.webshopmailservice.message.OrderConfirmationMessage;


@Service
public class OrderConfirmationService {

    private final MailService mailService;

    public OrderConfirmationService(MailService mailService) {
        this.mailService = mailService;
    }

    public void sendOrderConfirmation(OrderConfirmationMessage message) {
        mailService.sendMail(
                message.getCustomerEmail(),
                "Order Confirmation",
                buildEmailText(message)
        );
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


        System.out.println("=== ORDER CONFIRMATION ===");
        System.out.println(sb);
        System.out.println("==========================");
        
        return sb.toString();
    }
}