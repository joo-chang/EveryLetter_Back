package com.joo.everyletter_back.common.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 이메일 인증 대기 테이블
 *
 * 유저가 인증번호 확인 요청을 보내면 해당 테이블에서 인증 시간 체크 후 확인.
 * 인증 완료 시 삭제되고,인증하지 않은 로우 매일 삭제
 */
@Entity
@Table(name = "EMAIL_AUTH")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailAuth extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String authCode;
    private boolean authYn;

}
