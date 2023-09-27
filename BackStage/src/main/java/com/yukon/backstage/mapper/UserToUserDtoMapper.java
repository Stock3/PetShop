package com.yukon.backstage.mapper;


import com.yukon.backstage.dto.UserRequest;
import com.yukon.backstage.dto.UserResponse;
import com.yukon.backstage.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserToUserDtoMapper {
    UserEntity userDtoToUser(UserRequest userDto);

    UserResponse userToUserDto(UserEntity user);
}
