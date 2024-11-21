package com.example.user_management.feature.creation_audit;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

@Getter
@Setter
public class CreationAudit {

  @CreatedDate private LocalDate dateCreated;

  @LastModifiedDate private LocalDate dateModified;

  @CreatedBy private String userCreated;

  @LastModifiedBy private String userModified;
}
