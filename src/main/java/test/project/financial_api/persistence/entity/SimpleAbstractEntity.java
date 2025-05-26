package test.project.financial_api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleAbstractEntity {
  @Id
  @Column(name = "id")
  UUID id;
  
  @CreationTimestamp(source = SourceType.DB)
  @Column(name = "created_at", updatable = false)
  LocalDateTime createdAt;
  
  @UpdateTimestamp(source = SourceType.DB)
  @Column(name = "updated_at", insertable = false)
  LocalDateTime updatedAt;
}
