package com.example.Spring.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bankingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hệ Thống Banking API")
                        .description("Tài liệu và giao diện test tự động cho toàn bộ API quản lý tài khoản và giao dịch.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Developer")
                                .email("dev@banking.local")));
    }
}
