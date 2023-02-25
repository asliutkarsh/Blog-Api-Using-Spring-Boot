package com.utkarsh.blogappapis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@OpenAPIDefinition(security = {@SecurityRequirement(name = "Authorization",scopes = "global")})
@Configuration
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";


    @Bean
    public OpenAPI baseOpenAPI() throws IOException {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog Api Application")
                        .version("1.0.0")
                        .description("This Api is made by Utkarsh Jaiswal")
                        .contact(new Contact().name("Utkarsh Jaiswal")));
    }


}
