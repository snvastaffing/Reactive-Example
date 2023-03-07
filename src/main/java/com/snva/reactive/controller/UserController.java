package com.snva.reactive.controller;


import com.snva.reactive.model.User;
import com.snva.reactive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody User user){
        return  userService.createUser(user);
    }

    @GetMapping("/all")
    public Flux<User> getAllUsers(){
        return  userService.getAllUser();
    }


}
