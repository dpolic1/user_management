package com.example.user_management.feature.user;

import com.example.user_management.feature.jwt_token.JwtTokenService;
import com.example.user_management.feature.user.request.UserLoginRequest;
import com.example.user_management.feature.user.request.UserRegisterRequest;
import com.example.user_management.feature.user.request.UserUpdateRequest;
import com.example.user_management.feature.user.response.UserLoginResponse;
import com.example.user_management.feature.user.role.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@Transactional
@RequiredArgsConstructor
public class UserResourceService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JwtTokenService jwtTokenService;

  public User findById(Long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "User not found"));
  }

  public User findByUsername(String username) {
    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, "User not found"));
  }

  public UserLoginResponse login(UserLoginRequest loginRequest) {
    User user = findByUsername(loginRequest.getUsername());
    if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
      throw new ResponseStatusException(BAD_REQUEST, "Invalid password.");
    }
    return createLoginResponse(user);
  }

  public UserLoginResponse register(UserRegisterRequest userRegisterRequest) {
    User newUser = userMapper.toEntity(userRegisterRequest);
    newUser.setPassword(new BCryptPasswordEncoder().encode(userRegisterRequest.getPassword()));
    newUser.setRoles(Set.of(Role.ROLE_USER));
    userRepository.save(newUser);
    return createLoginResponse(newUser);
  }

  private UserLoginResponse createLoginResponse(User user) {
    UserLoginResponse userLoginResponse = new UserLoginResponse();
    userLoginResponse.setJwtToken(jwtTokenService.createJwt(user));
    userLoginResponse.setRoles(user.getRoles().stream().map(Enum::name).toList());
    return userLoginResponse;
  }

  public void logout() {
    String jwtToken = jwtTokenService.getCurrentUserJwtToken();
    jwtTokenService.invalidateToken(jwtToken);
  }

  public User update(String username, UserUpdateRequest request) {
    User user = findByUsername(username);
    return userRepository.save(userMapper.update(request, user));
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }
}