package com.example.user_management.feature.user;

import com.example.user_management.config.AuditorConfig;
import com.example.user_management.feature.user.request.UserLoginRequest;
import com.example.user_management.feature.user.request.UserRegisterRequest;
import com.example.user_management.feature.user.request.UserUpdateRequest;
import com.example.user_management.feature.user.response.UserLoginResponse;
import com.example.user_management.feature.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserResourceService userResourceService;
  private final UserMapper userMapper;
  private final AuditorConfig auditorConfig;

  @PostMapping("/login")
  public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest) {
    return userResourceService.login(userLoginRequest);
  }

  @PostMapping("/register")
  public UserLoginResponse register(@RequestBody UserRegisterRequest userRegisterRequest) {
    return userResourceService.register(userRegisterRequest);
  }

  @PostMapping("/logout")
  public void logout() {
    userResourceService.logout();
  }

  @GetMapping("/user-info")
  public UserResponse getUserInfo() {
    return userMapper.toResponse(
        userResourceService.getUserInfo(auditorConfig.getCurrentAuditor().get()));
  }

  @PutMapping()
  public UserResponse updateUser(@RequestBody UserUpdateRequest request) {
    return userMapper.toResponse(
        userResourceService.update(auditorConfig.getCurrentAuditor().get(), request));
  }

  @DeleteMapping()
  public void deleteUser() {
    userResourceService.delete(auditorConfig.getCurrentAuditor().get());
  }
}
