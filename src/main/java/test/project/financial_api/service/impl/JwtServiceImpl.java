package test.project.financial_api.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import test.project.financial_api.service.JwtService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
  
  @Value("${spring.security.key}")
  private String jwtSigningKey;
  
  @Value("${spring.security.token.expiration}")
  private Long tokenExpiration;
  
  public String extractSubject(final String token) {
    return extractClaim(token, Claims::getSubject);
  }
  
  public boolean isTokenValid(final String token, final UserDetails userDetails) {
    final String subject = extractSubject(token);
    return (subject.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }
  
  private <T> T extractClaim(final String token, final Function<Claims, T> claimsResolvers) {
    final Claims claims = extractAllClaims(token);
    return claimsResolvers.apply(claims);
  }
  
  public String generateToken(final UserDetails userDetails) {
    return Jwts.builder().subject(userDetails.getUsername())
      .issuedAt(new Date(System.currentTimeMillis()))
      .expiration(new Date(System.currentTimeMillis() + tokenExpiration * 60 * 1000))
      .signWith(getSigningKey()).compact();
  }
  
  private boolean isTokenExpired(final String token) {
    return extractExpiration(token).before(new Date());
  }
  
  private Date extractExpiration(final String token) {
    return extractClaim(token, Claims::getExpiration);
  }
  
  private Claims extractAllClaims(final String token) {
    return Jwts.parser()
      .setSigningKey(getSigningKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }
  
  private SecretKey getSigningKey() {
    final byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}