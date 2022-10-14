package com.ch.emailserver;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class EmailserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailserverApplication.class, args);
    }
    @Bean
    Queue queue(){
        return new Queue("vhr.email.welcome");
    }
}
