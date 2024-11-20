package com.example.user_management.feature.user.response;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginResponse {

  private String jwtToken;

  private String firstName;

  private String lastName;

  private String username;

  private String email;

  private List<String> roles;
}
