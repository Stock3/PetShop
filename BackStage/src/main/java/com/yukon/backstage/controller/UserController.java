package com.yukon.backstage.controller;

import com.yukon.backstage.dto.UserRequest;
import com.yukon.backstage.dto.UserResponse;
import com.yukon.backstage.mapper.UserToUserDtoMapper;
import com.yukon.backstage.service.UserService;
import com.yukon.backstage.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    private UserToUserDtoMapper mapper = Mappers.getMapper(UserToUserDtoMapper.class);


    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> userList = new ArrayList<>();
        userService.getAll().forEach(user -> userList.add(mapper.userToUserDto(user)));
        return ResponseEntity.ok(userList);
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserResponse>> getPaginated(@RequestParam("page") int page , @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        List<UserResponse> userList = new ArrayList<>();
        userService.getAll(pageable).forEach(user -> userList.add(mapper.userToUserDto(user)));
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id){
        UserResponse response = mapper.userToUserDto(userService.getById(id));
        return ResponseEntity.ok(response);
    }

/*    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserEntity> getUserByEmail(@PathVariable("email") String email){
        UserEntity UserEntity = userService.getByEmail(email);
        return ResponseEntity.ok(UserEntity);
    }*/

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse createUser(@RequestBody UserRequest user){
        return mapper.userToUserDto(userService.createUser(mapper.userDtoToUser(user)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable("id") Long id,
                                   @RequestBody UserRequest user){
        return mapper.userToUserDto(userService.updateUser(id, mapper.userDtoToUser(user)));
    }

}
