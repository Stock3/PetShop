package com.yukon.backstage.service;

import com.yukon.backstage.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserEntity> getAll();

    UserEntity getByEmail(String email);

    Page<UserEntity> getAll(Pageable pageable);

    UserEntity getById(Long id);

    void delete(Long id);

    UserEntity createUser(UserEntity userDto);

    UserEntity updateUser(Long id, UserEntity userDto);
}
