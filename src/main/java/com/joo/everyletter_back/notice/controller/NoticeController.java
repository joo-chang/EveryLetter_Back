package com.joo.everyletter_back.notice.controller;

import com.joo.everyletter_back.notice.DTO.*;
import com.joo.everyletter_back.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<NoticeListResp> noticeList(){

        NoticeListResp noticeListResp = noticeService.getNoticeList();

        return ResponseEntity.ok(noticeListResp);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<NoticeCreateResp> noticeCreate(@RequestBody NoticeCreateReq noticeCreateReq){


        NoticeCreateResp noticeCreateResp = noticeService.noticeCreate(noticeCreateReq);

        return ResponseEntity.ok(noticeCreateResp);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<NoticeUpdateResp> noticeCreate(@RequestBody NoticeUpdateReq noticeUpdateReq) {

        NoticeUpdateResp noticeUpdateResp = noticeService.noticeUpdate(noticeUpdateReq);

        return ResponseEntity.ok(noticeUpdateResp);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<NoticeDeleteResp> noticeCreate(@RequestBody NoticeDeleteReq noticeDeleteReq) {

        NoticeDeleteResp noticeDeleteResp = noticeService.noticeDelete(noticeDeleteReq);

        return ResponseEntity.ok(noticeDeleteResp);
    }
}
