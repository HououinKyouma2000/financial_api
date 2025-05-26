package test.project.financial_api.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "app_users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppUserEntity extends SimpleAbstractEntity implements UserDetails {
  
  @Column(name = "name", unique = true, nullable = false)
  String name;
  
  @Column(name = "password", nullable = false)
  String password;
  
  //TODO OneToMany
  @OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
  @JoinColumn(name = "account_id", nullable = false, unique = true, updatable = false)
  AccountEntity account;
  
  public void initialize(final String password, final AccountEntity account) {
    super.setId(UUID.randomUUID());
    this.password = password;
    this.account = account;
    
  }
  
  private static final String SINGLE_ROLE = "ROLE_USER";
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(SINGLE_ROLE));
  }
  
  @Override
  public String getUsername() {
    return getId().toString();
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
}
