package host.luke.knowbaby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SpringFoxConfig {

  @Bean
  public Docket createApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("host.luke.knowbaby.controller")) // 指定扫描的包
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Luke 懂宝 接口文档")
        .version("1.0")
        .license("The Apache License, Version 2.0")
        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
        .build();
  }

}