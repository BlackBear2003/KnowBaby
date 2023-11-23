package host.luke.knowbaby.config;

import host.luke.knowbaby.auth.JwtFilter;
import host.luke.knowbaby.auth.UserInfoInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

  @Bean
  public FilterRegistrationBean<JwtFilter> jwtFilter() {
    FilterRegistrationBean<JwtFilter> jwtFilter = new FilterRegistrationBean<>();

    jwtFilter.setFilter(new JwtFilter());
    jwtFilter.addUrlPatterns("/merchant/*");
    jwtFilter.addUrlPatterns("/merchandise/*");
    jwtFilter.addUrlPatterns("/customer/*");
    jwtFilter.addUrlPatterns("/chat/*");
    jwtFilter.setOrder(99);

    return jwtFilter;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new UserInfoInterceptor())
        .addPathPatterns("/**"); // 可以指定拦截的路径
  }

}
