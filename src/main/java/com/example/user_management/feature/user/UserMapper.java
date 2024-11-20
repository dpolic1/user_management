package com.example.user_management.feature.user;

import com.example.user_management.feature.user.request.UserRegisterRequest;
import com.example.user_management.feature.user.request.UserUpdateRequest;
import com.example.user_management.feature.user.response.UserLoginResponse;
import com.example.user_management.feature.user.response.UserResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "password", ignore = true)
  User toEntity(UserRegisterRequest userRegisterRequest);

  UserResponse toResponse(User user);

  @Mapping(target = "jwtToken", ignore = true)
  UserLoginResponse toLoginResponse(User user);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @InheritConfiguration(name = "toEntity")
  User update(UserUpdateRequest request, @MappingTarget User target);

}
