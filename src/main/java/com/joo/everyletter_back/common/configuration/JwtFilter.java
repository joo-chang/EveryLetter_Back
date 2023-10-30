package com.joo.everyletter_back.common.configuration;

import com.joo.everyletter_back.common.util.JwtUtil;
import com.joo.everyletter_back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final UserService userService;
    private final String secretKey;
    private final UserDetailsService userDetailsService;
    // 모든 요청을 막아놨는데 여기를 통해 권한을 부여해줄 수 있다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("Authorization : {}", authorization);

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Token 꺼내기
        String token = authorization.split(" ")[1];

        // Token Expired 됐는지
        if(!JwtUtil.validateToken(token, secretKey)){
            log.error("Token이 만료 되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // email Token에서 꺼내기
        String email = JwtUtil.getEmail(token, secretKey);
        log.info("email : {}", email);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            // 권한 부여
            UsernamePasswordAuthenticationToken authenticationToken =
    //                new UsernamePasswordAuthenticationToken(email, null, List.of(new SimpleGrantedAuthority("USER"))); // DB에서 꺼내서 role을 지정하면 됨
                    new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities()); // DB에서 꺼내서 role을 지정하면 됨
            // Detail을 넣어줌
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // request에 인증 도장이 찍힘
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        }
    }
}
