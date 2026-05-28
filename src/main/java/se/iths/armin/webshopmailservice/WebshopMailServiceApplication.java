package se.iths.armin.webshopmailservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class WebshopMailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebshopMailServiceApplication.class, args);
    }

}
