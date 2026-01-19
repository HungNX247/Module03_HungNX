package com.clinicbooking.mapper;

import com.clinicbooking.dto.UserDto;
import com.clinicbooking.model.entity.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        if(user == null) return null;

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setPhone(user.getPhone());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
