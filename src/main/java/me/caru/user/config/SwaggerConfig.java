package me.caru.user.config;

import java.util.Collections;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.configuration.ObjectMapperConfigured;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 *
 * @author sp.lee
 * @version 1.0.0
 * @since 2018. 08. 14.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			// TODO : 이곳에 키가 추가될 때 마다 global ignore 시켜주어야 함.
			.useDefaultResponseMessages(false)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo())
			.genericModelSubstitutes(Optional.class);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
			"Selective API",
			"Selective Client API.",
			"0.0.1",
			"",
			new Contact("Selective", "http://www.selective.me", "dl_selective_dev_server@navercorp.com"),
			"", "", Collections.emptyList());
	}

	@Component
	public static class SwaggerJacksonModule implements ApplicationListener<ObjectMapperConfigured> {
		@Override
		public void onApplicationEvent(ObjectMapperConfigured event) {
			event.getObjectMapper().registerModule(new Jdk8Module());
		}
	}
}
