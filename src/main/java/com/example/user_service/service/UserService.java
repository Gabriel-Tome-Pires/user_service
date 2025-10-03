package com.example.user_service.service;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    @Transactional
    public User CreateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User UpdateUser(User user, Long id) {
        User newUser= GetById(id);

        if(newUser.getName()!=null){
            newUser.setName(user.getName());
        }
        if(newUser.getEmail()!=null){
            newUser.setEmail(user.getEmail());
        }
        if(newUser.getAddress()!=null){
            newUser.setAddress(user.getAddress());
        }

        return userRepository.save(newUser);
    }

    @Transactional
    public void DeleteUser(User user) {
        userRepository.delete(user);
    }

    public User GetById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> GetAll(){
        return userRepository.findAll();
    }

    public List<User> GetByName(String name){
        return userRepository.findByNameContaining(name);
    }

}
