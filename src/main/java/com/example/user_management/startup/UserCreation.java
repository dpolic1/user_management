package com.example.user_management.startup;

import com.example.user_management.feature.user.UserRepository;
import com.example.user_management.feature.user.UserResourceService;
import com.example.user_management.feature.user.request.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreation {

  private final UserRepository userRepository;
  private final UserResourceService userResourceService;

  @EventListener(ApplicationReadyEvent.class)
  public void createBasicUsers() {
    if (!userRepository.existsByUsername("user")) {
      UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
      userRegisterRequest.setFirstName("user");
      userRegisterRequest.setLastName("user");
      userRegisterRequest.setUsername("user");
      userRegisterRequest.setEmail("user@user.com");
      userRegisterRequest.setPassword("user");
      userResourceService.register(userRegisterRequest);
    }
    if (!userRepository.existsByUsername("admin")) {
      UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
      userRegisterRequest.setFirstName("admin");
      userRegisterRequest.setLastName("admin");
      userRegisterRequest.setUsername("admin");
      userRegisterRequest.setEmail("admin@admin.com");
      userRegisterRequest.setPassword("admin");
      userResourceService.registerAdmin(userRegisterRequest);
    }
    System.out.println("User with basic role -> Username: user, Password: user");
    System.out.println("User with admin role -> Username: admin, Password: admin");
    System.out.println("Swagger available at: http://localhost:8080/swagger-ui/index.html");
  }
}
//yo