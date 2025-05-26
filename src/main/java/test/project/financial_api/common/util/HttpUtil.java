package test.project.financial_api.common.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpUtil {
  
  public static HttpHeaders headers(final HttpServletRequest request) {
    final HttpHeaders httpHeaders = new HttpHeaders();
    enumerationAsStream(request.getHeaderNames()).forEach(i -> httpHeaders.add(i, request.getHeader(i)));
    return httpHeaders;
  }
  
  public static <T> Stream<T> enumerationAsStream(final Enumeration<T> e) {
    return StreamSupport.stream(
      Spliterators.spliteratorUnknownSize(
        new Iterator<T>() {
          public T next() {
            return e.nextElement();
          }
          
          public boolean hasNext() {
            return e.hasMoreElements();
          }
        },
        Spliterator.ORDERED), false);
  }
  
}
