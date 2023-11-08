package com.joo.everyletter_back.common.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//package com.joo.everyletter_back.common.configuration;
//
//import com.joo.everyletter_back.common.util.JwtUtil;
//import com.joo.everyletter_back.user.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {
////
////    private final UserService userService;
////    private final String secretKey;
////    private final UserDetailsService userDetailsService;
////    // 모든 요청을 막아놨는데 여기를 통해 권한을 부여해줄 수 있다.
////    @Override
////    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
////        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
////        log.info("Authorization : {}", authorization);
////
////        if (authorization == null || !authorization.startsWith("Bearer ")) {
////            filterChain.doFilter(request, response);
////            return;
////        }
////        // Token 꺼내기
////        String token = authorization.split(" ")[1];
////
////        // Token Expired 됐는지
////        if(!JwtUtil.validateToken(token, secretKey)){
////            log.error("Token이 만료 되었습니다.");
////            filterChain.doFilter(request, response);
////            return;
////        }
////
////        // email Token에서 꺼내기
////        String email = JwtUtil.getEmail(token, secretKey);
////        log.info("email : {}", email);
////
////        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
////            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
////            // 권한 부여
////            UsernamePasswordAuthenticationToken authenticationToken =
////    //                new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority("USER"))); // DB에서 꺼내서 role을 지정하면 됨
////                    new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities()); // DB에서 꺼내서 role을 지정하면 됨
////            // Detail을 넣어줌
////            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////            // request에 인증 도장이 찍힘
////            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
////            filterChain.doFilter(request, response);
////        }
////    }
//}
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;

    // 실제 필터링 로직은 doFilterInternal 에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        // 1. Request Header 에서 토큰을 꺼냄
        String jwt = resolveToken(request);

        // 2. validateToken 으로 토큰 유효성 검사
        // 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보를 꺼내오기
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}