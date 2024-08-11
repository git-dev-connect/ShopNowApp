package com.shopnow.ShopNowApp.Security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource(@Value("${app.cors.allowed-origins}") List<String> allowedOrigins) {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // username and password are send along
        configuration.setAllowedOrigins(allowedOrigins);  // "https://example.com", "https://anotherdomain.com" domains
        configuration.addAllowedMethod("*");  //http methods (GET,POST,PUT,DELETE)
        configuration.addAllowedHeader("*");  //HEADERS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // above apply for whole application
        return source;
    }
}