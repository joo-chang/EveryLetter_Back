package com.joo.everyletter_back.common.configuration;

import com.joo.everyletter_back.common.jwt.JwtAccessDeniedHandler;
import com.joo.everyletter_back.common.jwt.JwtAuthenticationEntryPoint;
import com.joo.everyletter_back.common.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//package com.joo.everyletter_back.common.configuration;
//
//import com.joo.everyletter_back.user.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
////        http
////                .authorizeRequests()
////                .antMatchers("/api/hello").permitAll()
////                .anyRequest().authenticated();
////
////        return http.build();
////    }
//
//    private final AuthenticationProvider authenticationProvider;
//    private final UserService userService;
//    private final UserDetailsService userDetailsService;
//    @Value("${jwt.token.secret}")
//    private String secretKey;
//
//    /**
//     * 접근 제어
//     * @param httpSecurity
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//        return httpSecurity
//                .httpBasic().disable()
//                .csrf().disable() // 크로스 사이트
//                .cors().and() // 크로스 사이트 도메인이 다를때 허용
//                .authorizeRequests()
//                .antMatchers("/users/join/**", "/users/login").permitAll() // login, join은 인증 없이 접근 가능
//                .antMatchers("/api/**").hasAnyRole("ADMIN", "LETTER", "USER")
//                .antMatchers("/letter/**").hasAnyRole("ADMIN", "LETTER")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/**").authenticated() // 모든 POST 요청은 인증 필요
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilterBefore(new JwtFilter(userService, secretKey, userDetailsService), UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }
//
//}
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 설정 Disable
        http.csrf().disable()

                // exception handling 할 때 우리가 만든 클래스를 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 시큐리티는 기본적으로 세션을 사용
                // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/users/join/**", "/users/login").permitAll() // login, join은 인증 없이 접근 가능
                .antMatchers("/api/**").hasAnyRole("ADMIN", "LETTER", "USER")
                .antMatchers("/post/**").hasAnyRole("ADMIN", "LETTER", "USER")
                .antMatchers("/chat/**").hasAnyRole("ADMIN", "LETTER", "USER")
                .antMatchers("/letter/**").hasAnyRole("ADMIN", "LETTER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요

                // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
