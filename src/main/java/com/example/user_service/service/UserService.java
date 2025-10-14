package com.example.user_service.service;

import com.example.user_service.exception.ObjectNotFound;
import com.example.user_service.model.Users;
import com.example.user_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepository;

    @Transactional
    public Users createUser(Users users) {
        users.setCreatedAt(LocalDateTime.now());
        return userRepository.save(users);
    }

    @Transactional
    public Users updateUser(Users users, Long id) {
        Users existingUsers = getById(id);

        if(existingUsers.getName()!=null){
            existingUsers.setName(users.getName());
        }
        if(existingUsers.getEmail()!=null){
            existingUsers.setEmail(users.getEmail());
        }
        if(existingUsers.getAddress()!=null){
            existingUsers.setAddress(users.getAddress());
        }

        return userRepository.save(existingUsers);
    }

    @Transactional
    public void deleteUser(Users users) {
        userRepository.delete(users);
    }

    public Users getById(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFound("User not found with id " + id));
    }

    public List<Users> getAll(){
        return userRepository.findAll();
    }

    public List<Users> getByName(String name){
        return userRepository.findByNameContaining(name);
    }

}
