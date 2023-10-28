package com.joo.everyletter_back.common.configuration;

import com.joo.everyletter_back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http
//                .authorizeRequests()
//                .antMatchers("/api/hello").permitAll()
//                .anyRequest().authenticated();
//
//        return http.build();
//    }

    private final AuthenticationProvider authenticationProvider;
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    @Value("${jwt.token.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable() // 크로스 사이트
                .cors().and() // 크로스 사이트 도메인이 다를때 허용
                .authorizeRequests()
                .antMatchers("/users/join", "/users/login").permitAll() // login, join은 인증 없이 접근 가능
                .antMatchers("/api/**").hasAnyRole("ADMIN", "LETTER", "USER")
                .antMatchers("/letter/**").hasAnyRole("ADMIN", "LETTER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/**").authenticated() // 모든 POST 요청은 인증 필요
                .and()
                .authenticationProvider(authenticationProvider)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtFilter(userService, secretKey, userDetailsService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
