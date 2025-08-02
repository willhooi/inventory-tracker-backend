package com.simple_inventory_tracker.project.notification;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig{
    @Bean
    public NotificationService notificationService(){
        NotificationService notisrvc = new NotificationService();

        notisrvc.setSendTo("stockkeeper@gmail.com");
        return notisrvc;

    }
}