package com.example.user_management.feature.user.request;

import lombok.Data;

@Data
public class UserLoginRequest {

  private String username;

  private String password;
}
