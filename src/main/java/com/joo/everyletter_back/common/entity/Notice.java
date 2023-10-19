package com.joo.everyletter_back.common.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "NOTICE")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String title;
    private String contents;
    private String username;
}
