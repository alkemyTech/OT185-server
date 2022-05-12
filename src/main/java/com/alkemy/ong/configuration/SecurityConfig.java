package com.alkemy.ong.configuration;

import com.alkemy.ong.common.exception.handler.AuthenticationEntryPointHandler;
import com.alkemy.ong.common.exception.handler.CustomAccessDeniedHandler;
import com.alkemy.ong.common.security.filter.JwtRequestFilter;
import com.alkemy.ong.ports.input.rs.api.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/api/docs/**").permitAll()
                .antMatchers("/api/swagger-ui/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers(HttpMethod.POST, ApiConstants.CONTACTS_URI).permitAll()
                .antMatchers(HttpMethod.POST, ApiConstants.COMMENT_URI).authenticated()
                .antMatchers(HttpMethod.PUT, ApiConstants.COMMENT_URI + "/{id}").authenticated()
                .antMatchers(HttpMethod.DELETE, ApiConstants.COMMENT_URI + "/{id}").authenticated()
                .antMatchers(HttpMethod.GET, ApiConstants.CATEGORIES_URI).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, ApiConstants.USER_URI).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, ApiConstants.ORGANIZATIONS_URI + "/public/**").permitAll()
                .antMatchers(HttpMethod.POST).hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH).hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET).authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPointHandler())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
