package com.steve.Picgo.mapper;

import com.steve.Picgo.dtos.auth.RegisterRequest;
import com.steve.Picgo.entites.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUserEntity(RegisterRequest registerRequest);
}
