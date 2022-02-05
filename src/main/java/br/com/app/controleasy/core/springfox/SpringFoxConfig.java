package br.com.app.controleasy.core.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.OAS_30)
                .select().apis(RequestHandlerSelectors.basePackage("br.com.app.controleasy"))
                .build()
                .apiInfo(apiInfo());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Controleasy API")
                .description("API for workpoint registration control.").version("1.0")
                .contact(new Contact("Controleasy", "https://www.controleasy-app.com.br", "controleasy.app@gmail.com"))
                .build();
    }
}
