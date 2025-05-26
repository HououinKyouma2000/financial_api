package test.project.financial_api.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
  String extractSubject(String token);
  
  boolean isTokenValid(String token, UserDetails userDetails);
  
  String generateToken(UserDetails userDetails);
}
