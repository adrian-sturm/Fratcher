package com.asturm.webengineering_2017.fratcher.authentication;

import com.asturm.webengineering_2017.fratcher.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfiguration {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new JWTFilter(authenticationService, userService));
        bean.addUrlPatterns("/api/*");
        return bean;
    }
}

