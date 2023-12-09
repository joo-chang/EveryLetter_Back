package com.joo.everyletter_back.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joo.everyletter_back.auth.oauth.OauthProvider;
import com.joo.everyletter_back.common.enumeration.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email
    private String email;

    // 객체 응답 시 제거돼서 나감
    @JsonIgnore
    private String password;

    private String nickname;

    private String profileUrl;

    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Role role;

    @Enumerated(EnumType.STRING)
    private OauthProvider oauthProvider;

    @ColumnDefault("5")
    private Integer subLimit;

    // Collection은 필드에서 초기화 하는 것이 안전하다. new ArrayList<>();
    // Nullpointer Exception에 안전
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();


    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Reply> replies = new ArrayList<>();

}