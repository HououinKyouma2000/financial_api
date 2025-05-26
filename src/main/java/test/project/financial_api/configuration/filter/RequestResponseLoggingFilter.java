package test.project.financial_api.configuration.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import test.project.financial_api.common.util.HttpUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
  
  private static final RequestMatcher LOGGING_PATHS = new AntPathRequestMatcher("/api/**");
  
  @Override
  protected void doFilterInternal(
    final HttpServletRequest request,
    final HttpServletResponse response,
    final FilterChain filterChain
  ) throws ServletException, IOException {
    
    if (!LOGGING_PATHS.matches(request)) {
      filterChain.doFilter(request, response);
      return;
    }
    
    final ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    final ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
    
    try {
      filterChain.doFilter(requestWrapper, responseWrapper);
    } finally {
      try {
        final String requestBody = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        final String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        
        log.info("""
            [REQUEST] {} {}
            Headers: {}
            Body: {}
            """,
          request.getMethod(), request.getRequestURI(),
          HttpUtil.headers(request), requestBody);
        
        log.info("""
            [RESPONSE] Status: {}
            Body: {}
            """,
          response.getStatus(), responseBody
        );
        
        responseWrapper.copyBodyToResponse();
      } catch (final Exception ex) {
        log.error("Logging: {}", ex.getMessage());
      }
    }
  }
}
