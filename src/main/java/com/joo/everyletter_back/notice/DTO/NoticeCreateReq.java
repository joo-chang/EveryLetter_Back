package com.joo.everyletter_back.notice.DTO;

import lombok.Getter;

@Getter
public class NoticeCreateReq {
    private String title;
    private String contents;
    private String username;
}
