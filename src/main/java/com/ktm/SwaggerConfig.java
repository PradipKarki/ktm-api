package com.ktm;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@PropertySource("classpath:messages.properties")
@EnableSwagger2
public class SwaggerConfig {

  @Autowired
  Environment env;

  @Bean
  public Docket twitterApi() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.ktm"))
      .paths(PathSelectors.any())
      .build().apiInfo(metaData());

  }

  private ApiInfo metaData() {
    return new ApiInfo(this.env.getProperty("SwaggerConfig.Title"),
      this.env.getProperty("SwaggerConfig.Description"), //$NON-NLS-1$ //$NON-NLS-2$
      this.env.getProperty("SwaggerConfig.Version"),
      this.env.getProperty("SwaggerConfig.TermsofServiceUrl"), //$NON-NLS-1$ //$NON-NLS-2$
      new Contact(this.env.getProperty("SwaggerConfig.Name"),
        this.env.getProperty("SwaggerConfig.Url"), //$NON-NLS-1$ //$NON-NLS-2$
        this.env.getProperty("SwaggerConfig.Email")),
      this.env.getProperty("SwaggerConfig.License"), //$NON-NLS-1$ //$NON-NLS-2$
      this.env.getProperty("SwaggerConfig.LicenseUrl"),
      Collections.emptyList()); //$NON-NLS-1$
  }

}