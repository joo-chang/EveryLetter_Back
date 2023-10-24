package com.joo.everyletter_back.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.user.dto.UserJoinReq;
import com.joo.everyletter_back.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    void join() throws Exception {
        String email = "jooc01@gmail.com";
        String password = "123qwe";

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinReq(email, password))))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("회원가입 실패 - email 중복")
    void join_fail() throws Exception {
        String email = "jooc01@gmail.com";
        String password = "123qwe";

        when(userService.join(any(), any()))
                .thenThrow(ServiceException.INTERNAL_SERVER_ERROR);
        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinReq(email, password))))
                .andDo(print())
                .andExpect(status().is5xxServerError());

    }

}