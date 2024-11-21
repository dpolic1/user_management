package com.example.user_management.feature.example_entity;

import com.example.user_management.feature.creation_audit.CreationAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "example_entities")
public class ExampleEntity extends CreationAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;
}
