package com.example.user_management.config;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@RequiredArgsConstructor
public class AuditorConfig implements AuditorAware<String> {

  @Override
  public @NotNull Optional<String> getCurrentAuditor() {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication != null
          && authentication.isAuthenticated()
          && authentication.getPrincipal() instanceof Jwt jwt) {
        return Optional.ofNullable(jwt.getClaimAsString("sub"));
      }

      return Optional.ofNullable(
          RequestContextHolder.getRequestAttributes() != null ? getUserSub() : null);
    } catch (Exception e) {
      return Optional.of("SYSTEM");
    }
  }

  private String getUserSub() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null
        && authentication.isAuthenticated()
        && authentication.getPrincipal() instanceof Jwt jwt) {
      return jwt.getClaimAsString("sub");
    }
    return null;
  }
}
