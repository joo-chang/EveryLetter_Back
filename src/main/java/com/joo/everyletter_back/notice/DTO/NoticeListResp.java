package com.joo.everyletter_back.notice.DTO;

import com.joo.everyletter_back.common.entity.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class NoticeListResp {
    private List<Notice> notices;
}
