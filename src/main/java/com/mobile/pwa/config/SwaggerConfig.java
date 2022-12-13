package com.mobile.pwa.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

	 	@Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
	        registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
	        registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
	        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
	    } 
	 
	 	@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
	        registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }    
	 
	 	@Bean
	    public Docket mobileAPI () {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.mobile.pwa"))
	                .paths(PathSelectors.ant("/**"))
	                .build()
	                .apiInfo(metaInfo());
	    }
	 	
	 	private ApiInfo metaInfo() {
	        var apiInfo = new ApiInfo(
	                "Workstation Reservation system",
	                "API REST Mobile PWA",
	                "1.0",
	                "Terms of Service",
	                new Contact("Squad SiDi", "https://www.sidi.org.br/", ""),
	                "Apache License Version 2.0",
	                "https://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>()
	        );
	        return apiInfo;
	    }   
	
}
