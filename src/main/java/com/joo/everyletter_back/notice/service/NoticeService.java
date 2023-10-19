package com.joo.everyletter_back.notice.service;

import com.joo.everyletter_back.common.entity.Notice;
import com.joo.everyletter_back.common.repository.NoticeRepository;
import com.joo.everyletter_back.notice.DTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeListResp getNoticeList() {

        List<Notice> noticeList = noticeRepository.findAll();

        return NoticeListResp.builder().notices(noticeList).build();
    }

    public NoticeCreateResp noticeCreate(NoticeCreateReq noticeCreateReq) {
        Notice notice = Notice.builder()
                .title(noticeCreateReq.getTitle())
                .contents(noticeCreateReq.getContents())
                .username(noticeCreateReq.getUsername())
                .build();
        noticeRepository.save(notice);

        return NoticeCreateResp.builder().seq(notice.getSeq()).build();
    }

    public NoticeUpdateResp noticeUpdate(NoticeUpdateReq noticeUpdateReq){

        Notice notice = noticeRepository.findById(noticeUpdateReq.getSeq()).orElseThrow();
        notice.setTitle(noticeUpdateReq.getTitle());
        notice.setContents(noticeUpdateReq.getContents());

        noticeRepository.save(notice);

        return NoticeUpdateResp.builder().seq(notice.getSeq()).build();
    }

    public NoticeDeleteResp noticeDelete(NoticeDeleteReq noticeDeleteReq) {

        noticeRepository.deleteById(noticeDeleteReq.getSeq());

        return NoticeDeleteResp.builder().seq(noticeDeleteReq.getSeq()).build();
    }
}
