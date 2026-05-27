package se.iths.armin.webshopmailservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailServiceConfig {

    @Bean
    public se.iths.armin.mailservice.MailService arminMailService(
            JavaMailSender mailSender,
            @Value("${spring.mail.username}") String from
    ) {
        return new se.iths.armin.mailservice.MailService(mailSender, from);
    }
}