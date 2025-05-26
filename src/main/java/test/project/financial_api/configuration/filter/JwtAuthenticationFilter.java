package test.project.financial_api.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import test.project.financial_api.service.JwtService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  
  public static final String BEARER_PREFIX = "Bearer ";
  public static final String HEADER_NAME = "Authorization";
  
  JwtService jwtService;
  UserDetailsService userDetailsService;
  
  @Override
  protected void doFilterInternal(
    final HttpServletRequest request,
    final @NonNull HttpServletResponse response,
    final @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    
    final String authHeader = request.getHeader(HEADER_NAME);
    if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, BEARER_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }
    
    final String jwt = authHeader.substring(BEARER_PREFIX.length());
    final String subject = jwtService.extractSubject(jwt);
    
    if (StringUtils.isNotEmpty(subject) && SecurityContextHolder.getContext().getAuthentication() == null) {
      final UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
      
      
      if (jwtService.isTokenValid(jwt, userDetails)) {
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        
        final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          null,
          userDetails.getAuthorities()
        );
        
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
      }
    }
    filterChain.doFilter(request, response);
  }
}