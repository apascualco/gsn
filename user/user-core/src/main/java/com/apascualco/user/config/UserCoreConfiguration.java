package com.apascualco.user.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UserCoreConfiguration {

    private final Integer encodingStrength;

    public UserCoreConfiguration(
            @Value("${security.encoding-strength:16}") final Integer encodingStrength
    ) {
        this.encodingStrength = encodingStrength;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(encodingStrength);
    }
}
