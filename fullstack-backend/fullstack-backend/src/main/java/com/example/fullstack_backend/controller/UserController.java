package com.example.fullstack_backend.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import com.example.fullstack_backend.exception.UserNotFoundException;
import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/user_controller")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);

    }

    @GetMapping("/getAllUser")
    List<User> getAllUsers(){
        return  userRepository.findAll();

    }



    @GetMapping("/getById/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user ->{
            user.setUsername(newUser.getUsername());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(()->new UserNotFoundException(id));

    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id "+id+" has been deleted successfully.";
    }

}
