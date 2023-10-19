package com.joo.everyletter_back.notice.DTO;

import lombok.Getter;

@Getter
public class NoticeUpdateReq {
    private Long seq;
    private String title;
    private String contents;
}
