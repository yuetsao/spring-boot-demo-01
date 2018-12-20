package com.example.demo.controller;


import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController (UserRepository repository)  {
        this.userRepository = repository;
    }

    @PostMapping("/person/save")
    public User save(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        if(userRepository.save(user)) {
            System.out.println("save successs");
        }
        return user;
    }
}
