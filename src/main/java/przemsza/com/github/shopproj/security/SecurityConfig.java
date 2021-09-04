package przemsza.com.github.shopproj.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/menu/**", "/booking/**", "/css/**", "/images/**","/order/**", "/add/**", "/address/**", "/console/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin();

            http.csrf().disable();
            http.headers().frameOptions().disable();
    }
}
