package com.joo.everyletter_back.user.controller;

import com.joo.everyletter_back.user.dto.UserJoinReq;
import com.joo.everyletter_back.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinReq userJoinReq) {

        userService.join(userJoinReq.getEmail(), userJoinReq.getPassword());

        return ResponseEntity.ok().body("회원가입 성공!");
    }
}
