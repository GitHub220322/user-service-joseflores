package com.softcoders.userservice.controller;

import com.softcoders.userservice.dto.UserDto;
import com.softcoders.userservice.entity.User;
import com.softcoders.userservice.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService){
        this.userService=userService;
    }

    @GetMapping()
    public Flux<UserDto> getAll() {
        return this.userService.getAll();
    }

    @PostMapping
    public Mono<UserDto> createUser(@Valid @RequestBody Mono<UserDto> userDtoMono) {
        return this.userService.createUser(userDtoMono);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> getUserById(@PathVariable Integer id) {
        return this.userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable int id, @Valid @RequestBody Mono<UserDto> userDtoMono){
        return this.userService.updateUser(id, userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable int id){
        return this.userService.deleteUser(id);
    }

}
