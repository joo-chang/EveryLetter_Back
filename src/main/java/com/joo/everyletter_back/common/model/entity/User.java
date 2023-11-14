package com.joo.everyletter_back.common.model.entity;

import com.joo.everyletter_back.common.enumeration.Role;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ColumnDefault("5")
    private Integer subLimit;

}

    //    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> collectors = new ArrayList<>();
//        collectors.add(()->{return String.valueOf(getRole());}); // ROLE_USER
//        return collectors;
//    }
