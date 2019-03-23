package com.ktm;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Value("${SwaggerConfig.Title}")
  private String title;
  @Value("${SwaggerConfig.Description}")
  private String description;
  @Value("${SwaggerConfig.Version}")
  private String version;
  @Value("${SwaggerConfig.TermsofServiceUrl}")
  private String termsOfServiceUrl;
  @Value("${SwaggerConfig.Name}")
  private String name;
  @Value("${SwaggerConfig.Url}")
  private String url;
  @Value("${SwaggerConfig.Email}")
  private String email;
  @Value("${SwaggerConfig.License}")
  private String license;
  @Value("${SwaggerConfig.LicenseUrl}")
  private String licenseUrl;

  @Bean
  public Docket twitterApi1() {
    return new Docket(DocumentationType.SWAGGER_2)
      .groupName("1. ktm-times app")
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.ktm"))
      .paths(PathSelectors.any())
      .build().apiInfo(metaData());
  }

  @Bean
  public Docket twitterApi2() {
    return new Docket(DocumentationType.SWAGGER_2)
      .groupName("2. application health/metrics")
      .select()
      .apis(RequestHandlerSelectors.basePackage("org.springframework"))
      .paths(PathSelectors.regex("(?!/error).+"))
      .build().apiInfo(metaData());
  }

  private ApiInfo metaData() {
    return new ApiInfo((title), (description), (version), (termsOfServiceUrl),
      new Contact((name), (url), (email)), (license), (licenseUrl), Collections
      .emptyList());
  }

}