package com.example.user_management.feature.example_entity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class ExampleEntityController {

  private final ExampleEntityRepository exampleEntityRepository;

  @GetMapping("test-authenticated")
  public String testAuthenticated() {
    return "Authenticated";
  }

  @GetMapping("test-admin")
  public String testAdmin() {
    return "Admin";
  }
}
