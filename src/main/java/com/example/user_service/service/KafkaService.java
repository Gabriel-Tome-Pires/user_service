package com.example.user_service.service;

import com.example.user_service.model.Users;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    private final KafkaTemplate<String, Users> kafkaTemplateUser;

    public KafkaService(@Qualifier("usersKafkaTemplate") KafkaTemplate<String, Users> kafkaTemplateUser) {
        this.kafkaTemplateUser = kafkaTemplateUser;
    }

    @SuppressWarnings("null")
    public void sendMessageUserCreated(Users users) {
        String key= users.getId().toString();
        System.out.println("User created: " + key);

        kafkaTemplateUser.send("user-created", key, users).whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Failed to send user created: " + ex.getMessage());
            } else {
                System.out.println("User created sent successfully: " + users.getId());
            }
        });
    }

}
