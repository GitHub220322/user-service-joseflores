package com.softcoders.userservice.service;

import com.softcoders.userservice.dto.UserDto;
import com.softcoders.userservice.entity.User;
import com.softcoders.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,ModelMapper modelMapper){
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }
    public Flux<UserDto> getAll() {
        return this.userRepository.findAll()
                .map(this::converterToDto);
    }

    public Mono<UserDto> createUser(Mono<UserDto> userDtoMono) {
        return userDtoMono.map(this::converterToEntity)
                .flatMap(this.userRepository::save)
                .map(this::converterToDto);

    }

    public Mono<UserDto> getUserById(Integer id) {
        return this.userRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> updateUser(int id, Mono<UserDto> userDtoMono){
        return this.userRepository.findById(id)
                .flatMap(u -> userDtoMono
                        .map(this::converterToEntity)
                        .doOnNext(e -> e.setId(id)))
                .flatMap(this.userRepository::save)
                .map(this::converterToDto);
    }
    public Mono<Void> deleteUser(int id){
        return this.userRepository.deleteById(id);
    }

    public UserDto converterToDto(User entity){
        return modelMapper.map(entity,UserDto.class);
    }
    public User converterToEntity(UserDto dto){
        return modelMapper.map(dto,User.class);
    }
}
