package com.yukon.backstage;


import com.yukon.backstage.entity.UserEntity;
import com.yukon.backstage.enums.UserRole;
import com.yukon.backstage.repository.UserRepository;
import com.yukon.backstage.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity testUser;

    @BeforeEach
    void setUp()
    {
        testUser = new UserEntity(null,"Qwerty", "Ihor", "Qwertiovych", "customer@gmail.com", "12344321", "0973234532", UserRole.CUSTOMER, false);
    }



    @Test
    void createUserSuccessTest()
    {
        UserEntity user = new UserEntity(1L,"Qwerty", "Ihor", "Qwertiovych", "customer@gmail.com", "12344321", "0973234532", UserRole.CUSTOMER, false, Instant.now(), Instant.now());
        given(userRepository.save(testUser)).willReturn(user);

        UserEntity savedUser = userService.createUser(testUser);

        assertThat(savedUser).isNotNull();
        assertEquals(user,savedUser);

    }

    @Test
    void updateUserSuccessTest(){
        UserEntity user = new UserEntity(1L,"Qwerty", "Ihor", "Qwertiovych", "customer@gmail.com", "12344321", "0973234532", UserRole.CUSTOMER, false, Instant.now(), Instant.now());
        given(userRepository.save(testUser)).willReturn(user);
        given(userRepository.findById(1000L)).willReturn(Optional.of(user));
        UserEntity resultUser = userService.updateUser(1000L,testUser);

        assertThat(resultUser).isNotNull();
        assertEquals(user, resultUser);
    }

    @Test
    void getAllUsersSuccess()
    {
        UserEntity user = new UserEntity(1L,"Qwerty", "Ihor", "Qwertiovych", "customer@gmail.com", "12344321", "0973234532", UserRole.CUSTOMER, false, Instant.now(), Instant.now());
        List<UserEntity> users = new ArrayList<>();
        users.add(user);
        given(userRepository.findAll()).willReturn(users);
        List<UserEntity> expected = userService.getAll();
        assertEquals(expected, users);
    }

    @Test
    void userDeleteTest(){
        Long userId = 1L;

        userService.delete(userId);
        userService.delete(userId);

        verify(userRepository, times(2)).deleteById(userId);
    }

    @Test
    void userFindByIdSuccessTest(){
        Long id = 1L;
        UserEntity user = new UserEntity(1L,"Qwerty", "Ihor", "Qwertiovych", "customer@gmail.com", "12344321", "0973234532", UserRole.CUSTOMER, false, Instant.now(), Instant.now());
        given(userRepository.findById(id)).willReturn(Optional.of(user));
        UserEntity expected = userService.getById(id);

        assertThat(expected).isNotNull();
        assertEquals(user, expected);

    }

    @Test
    void getAllUsersPageableSuccessTest()
    {
        Pageable pageable = PageRequest.of(0,1);
        UserEntity user = new UserEntity(1L,"Qwerty", "Ihor", "Qwertiovych", "customer@gmail.com", "12344321", "0973234532", UserRole.CUSTOMER, false, Instant.now(), Instant.now());
        List<UserEntity> users = new ArrayList<>();
        users.add(user);
        given(userRepository.findAll(pageable)).willReturn(new PageImpl<UserEntity>(users));
        List<UserEntity> expected = userService.getAll(pageable).stream().toList();
        assertEquals(expected, users);
    }

    @Test
    void userFindByEmailTest(){
        UserEntity user = new UserEntity(1L,"Qwerty", "Ihor", "Qwertiovych", "customer@gmail.com", "12344321", "0973234532", UserRole.CUSTOMER, false, Instant.now(), Instant.now());
        given(userRepository.findByEmail(testUser.getEmail())).willReturn(user);
        UserEntity expected = userService.getByEmail(testUser.getEmail());

        assertThat(expected).isNotNull();
        assertEquals(user, expected);
    }





}
