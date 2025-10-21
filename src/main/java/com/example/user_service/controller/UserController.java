package com.example.user_service.controller;

import com.example.user_service.model.Users;
import com.example.user_service.service.KafkaService;
import com.example.user_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final KafkaService kafkaService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        Users newUsers =userService.createUser(users);

        kafkaService.sendMessageUserCreated(newUsers);

        return ResponseEntity.ok(newUsers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users users, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUser(users, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Users users = userService.getById(id);
        userService.deleteUser(users);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Users>> getUsersByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.getByName(name));
    }
}
