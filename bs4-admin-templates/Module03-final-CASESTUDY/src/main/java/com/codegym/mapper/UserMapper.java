package com.codegym.mapper;

import com.codegym.model.dto.UserDto;
import com.codegym.model.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());
        return dto;
    }
}
