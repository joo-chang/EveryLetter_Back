package com.joo.everyletter_back.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joo.everyletter_back.common.exception.ServiceException;
import com.joo.everyletter_back.user.dto.UserJoinReq;
import com.joo.everyletter_back.user.dto.UserLoginReq;
import com.joo.everyletter_back.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@MockBean(JpaMetamodelMappingContext.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join() throws Exception {
        String email = "jooc03@gmail.com";
        String password = "123qwe";
        String nickname = "창창";

        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinReq(email, password, nickname))))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("회원가입 실패 - email 중복")
    @WithMockUser
    void join_fail() throws Exception {
        String email = "jooc03@gmail.com";
        String password = "123qwe";
        String nickname = "창창";

        when(userService.join(any()))
                .thenThrow(ServiceException.INTERNAL_SERVER_ERROR);
        mockMvc.perform(post("/api/v1/users/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinReq(email, password, nickname))))
                .andDo(print())
                .andExpect(status().is5xxServerError());

    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    void login_success() throws Exception {
        String email = "jooc02@gmail.com";
        String password = "123qwe2@";


        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginReq(email, password))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인 실패 - email 없음")
    @WithMockUser
    void login_failemail() throws Exception {
        String email = "jooc01@gmail.com";
        String password = "123qwe";

        when(userService.login(any(), any()))
                .thenThrow(ServiceException.USER_NOT_FOUND);

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginReq(email, password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 @- 패스워드 틀림")
    @WithMockUser
    void login_failpassword() throws Exception {
        String email = "jooc01@gmail.com";
        String password = "123qwe";

        when(userService.login(any(), any()))
                .thenThrow(ServiceException.WRONG_EMAIL_OR_PASSWORD);

        mockMvc.perform(post("/api/v1/users/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginReq(email, password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}