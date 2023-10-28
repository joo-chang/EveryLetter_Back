package com.joo.everyletter_back.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReviewController {
    @PostMapping("/reviews")
    public ResponseEntity<String> review(Authentication authentication) {
        return ResponseEntity.ok().body("님의 리뷰 등록 완료");
    }
}
