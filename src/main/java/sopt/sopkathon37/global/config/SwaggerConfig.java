package sopt.sopkathon37.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
            .title("Android3 Server API Document")
            .version("v1.0.0")
            .description("서버 API Swagger 명세서입니다.")
            .contact(
                new Contact().email("android3server@gmail.com")
            );

        return new OpenAPI()
            .addServersItem(new Server().url("http://localhost:8080"))
            .addServersItem(new Server().url("http://3.35.37.190"))
            .info(info);
    }

    /**
     * 예시 그룹
     */
    @Bean
    public GroupedOpenApi groupArtist() {
        return GroupedOpenApi.builder()
            .group("example")
            .pathsToMatch("/examples/**")
            .build();
    }
}
