package com.yukon.backstage.service.impl;

import com.yukon.backstage.entity.UserEntity;
import com.yukon.backstage.exception.ObjectNotFoundException;
import com.yukon.backstage.repository.UserRepository;
import com.yukon.backstage.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserEntity> getAll() {
        List<UserEntity> users = userRepository.findAll();
        return users;
    }

    public Page<UserEntity> getAll(Pageable pageable)
    {
        Page<UserEntity> page = userRepository.findAll(pageable);
        return page;
    }

    public UserEntity getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Entity was not found  ID: ", id));
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserEntity createUser(UserEntity newUser) {
        return userRepository.save(newUser);
    }

    @Transactional
    public UserEntity updateUser(Long id, UserEntity newUser){
        UserEntity user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        newUser.setId(id);
        newUser.setCreatedAt(user.getCreatedAt());
        return userRepository.save(newUser);

    }
}

