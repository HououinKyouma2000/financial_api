package test.project.financial_api.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.project.financial_api.persistence.repository.AppUserEntityRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsServiceImpl implements UserDetailsService {
  
  AppUserEntityRepository appUserEntityRepository;
  
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return appUserEntityRepository.findById(UUID.fromString(username)).orElseThrow(() -> new UsernameNotFoundException(username));
  }
}
