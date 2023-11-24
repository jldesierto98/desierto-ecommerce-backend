package com.desierto.ecommerce.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeRequests(configurer ->
                configurer
                        .antMatchers("/order/**")
                        .authenticated())
                .oauth2ResourceServer()
                .jwt();

        //support cors filters
        http.cors();

        // add content negotiation
        http.setSharedObject(ContentNegotiationStrategy.class,
                            new HeaderContentNegotiationStrategy());

        // use OKTA response for unauthorized response.
        Okta.configureResourceServer401ResponseBody(http);

        return http.build();

    }
}
