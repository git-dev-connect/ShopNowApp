package com.shopnow.ShopNowApp.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    // is used to validate the authentication request by checking the provided credentials against the configured authentication providers (like username/password
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/public/**", "/auth/**").permitAll()
                        .requestMatchers("/authenticate").permitAll()
//                        .requestMatchers("/ws/**").permitAll()
//                        .requestMatchers("/app/**").permitAll()
//                        .requestMatchers("/app/sendMessage").permitAll()
//                        .requestMatchers("/topic/**").permitAll()
                        .requestMatchers("/", "/public/**", "/auth/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())   //applies the default configuration for HTTP Basic authentication
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // dont use session's like in portal
                .cors(Customizer.withDefaults())   //to enable CORS with default settings
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}


/*
+------------------+                   +--------------------------------------+
|  Client Request  |                   | The initial request made by the client. |
+--------+---------+                   +--------------------------------------+
         |
         v
+--------+---------+                   +--------------------------------------+
| Authentication   |                   | Extracts credentials (username, password) from the request. |
|  Filter          |                   +--------------------------------------+
+--------+---------+
         |
         v
+--------+---------+                   +--------------------------------------+
| Authentication   |                   | Manages the authentication process. It uses an AuthenticationProvider to check credentials. |
|  Manager         |                   +--------------------------------------+
+--------+---------+
         |
         v
+--------+---------+                   +--------------------------------------+
| Authentication   |                   | Checks the provided credentials against stored data using DaoAuthenticationConfigurer
|  Provider        |                   +--------------------------------------+
+--------+---------+
         |
         v
+--------+---------+                   +--------------------------------------+
|UserDetailsService|                   | Retrieves user details (e.g., roles, permissions) from the database. |
|(loadUserByUsername)|                   +--------------------------------------+
+--------+---------+
         |
         v
+--------+---------+                   +--------------------------------------+
| Validate Password |                  | Verifies the password using the password encoder. |
+--------+---------+                   +--------------------------------------+
         |
         v
+--------+---------+                   +--------------------------------------+
| Set Security     |                   | Stores the authentication details in the security context for access control. |
|  Context         |                   +--------------------------------------+
+--------+---------+
         |
         v
+--------+---------+                   +--------------------------------------+
| Forward Request  |                   | Passes the request to the appropriate controller if authentication is successful. |
|  to Controller   |                   +--------------------------------------+
+------------------+


*/



