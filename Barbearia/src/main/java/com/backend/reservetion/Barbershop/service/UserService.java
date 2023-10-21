package com.backend.reservetion.Barbershop.service;

import com.backend.reservetion.Barbershop.dtos.UserDTOs;
import com.backend.reservetion.Barbershop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.reservetion.Barbershop.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createdUser(UserDTOs userDTOs) {
        User newUser = new User(userDTOs);
        userRepository.save(newUser);
        return newUser;
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User findById(Long id){
        return userRepository.findById(id).get();
    }
    public User updateUser(Long id, UserDTOs userDTOs){
        User updateUser = findById(id);
        updateUser.setFirstName(userDTOs.firstName());
        updateUser.setLastName(userDTOs.lastName());
        updateUser.setEmail(userDTOs.email());
        return userRepository.save(updateUser);
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }

}
