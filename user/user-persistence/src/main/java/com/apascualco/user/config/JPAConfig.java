package com.apascualco.user.config;

import com.apascualco.user.model.UserEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = UserEntity.class)
@EnableJpaRepositories(basePackages = "com.apascualco")
@ComponentScan(basePackages = "com.apascualco")
public class JPAConfig {
}
