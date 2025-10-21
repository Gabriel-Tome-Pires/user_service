package com.example.user_service.repository;

import com.example.user_service.model.Address;
import com.example.user_service.model.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "/application-test.properties")
public class TestUserRepository {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void TestCreateUser_WithValidData_ReturnsUser() {
        Users user = new Users(
                new Address("A","B","C"),
                LocalDateTime.now(),
                "fulano@gmail.com", "fulano");

        Users newUser=userRepository.save(user);

        Assertions.assertNotNull(newUser);
        Assertions.assertEquals(user.getEmail(), newUser.getEmail());
        Assertions.assertEquals(user.getName(), newUser.getName());
        Assertions.assertEquals(user.getCreatedAt(), newUser.getCreatedAt());
    }

    @Test
    public void TestDeleteUser_WithValidData() {
        Users user = new Users(
                new Address("A","B","C"),
                LocalDateTime.now(),
                "fulano@gmail.com", "fulano");
        Users newUser=userRepository.save(user);

        userRepository.delete(newUser);
    }

    @Test
    public void TestGetUserById_WithValidData_ReturnsUser() {
        Users user = new Users(
                new Address("A","B","C"),
                LocalDateTime.now(),
                "fulano@gmail.com", "fulano");
        Users newUser=userRepository.save(user);

        Users gettedUser=userRepository.findById(newUser.getId()).get();

        Assertions.assertNotNull(gettedUser);
        Assertions.assertEquals(gettedUser.getName(), newUser.getName());
        Assertions.assertEquals(gettedUser.getEmail(), newUser.getEmail());
    }

    @Test
    public void TestGetAllUsers_WithValidData_ReturnsAllUsers() {
        List<Users> users=userRepository.findAll();

        Assertions.assertNotNull(users);
    }
}
