package dev.pndev.stock.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Estoque de Produtos - PnDev")
                        .version("1.0")
                        .description("Documentação da API de Estoque de Produtos com Springdoc")
                        .contact(new Contact()
                                .name("Priscila Nascimento")
                                .email("pnascimento2808@gmail.com")
                        ));
    }
}
