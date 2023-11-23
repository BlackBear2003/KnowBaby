package host.luke.knowbaby.auth;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import host.luke.knowbaby.constant.RequestContextConstant;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

public class JwtFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

//    HttpServletRequest request = ((HttpServletRequest) servletRequest);
//    HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//    String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

    // TODO: double-token authentication
    filterChain.doFilter(servletRequest, servletResponse);
  }
}

