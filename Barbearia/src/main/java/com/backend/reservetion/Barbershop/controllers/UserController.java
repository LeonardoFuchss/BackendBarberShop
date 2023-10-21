package com.backend.reservetion.Barbershop.controllers;

import com.backend.reservetion.Barbershop.dtos.UserDTOs;
import com.backend.reservetion.Barbershop.domain.User;
import com.backend.reservetion.Barbershop.exceptions.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.backend.reservetion.Barbershop.service.UserService;

import java.util.List;

@Validated

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createdUser(@Valid @RequestBody UserDTOs userDTOs) throws UserException {
        User newUser;
        try {
            newUser = userService.createdUser(userDTOs);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new UserException("Erro ao criar um usuário!");
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws UserException {
        try {
            List<User> users = userService.getAll();
            return ResponseEntity.ok(users);

        } catch (Exception e) {
            throw new UserException("Erro ao buscar usuários.");
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long Id) throws UserException {
        try {
            User userById = userService.findById(Id);
            return ResponseEntity.ok(userById);

        } catch (Exception e) {
            throw new UserException("Erro ao buscar usuário por Id.");
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, UserDTOs userDTOs) throws UserException {
        try {
            User newUser = userService.updateUser(id, userDTOs);
            return ResponseEntity.ok().body(newUser);

        } catch (Exception e){
            throw new UserException("Erro ao atualizar o usuário.");
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable Long id) throws UserException {
        try {
            userService.delete(id);

        } catch (Exception e){
            throw new UserException("Erro ao deleter o usuário");
        }
    }
}

