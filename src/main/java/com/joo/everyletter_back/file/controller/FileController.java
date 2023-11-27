package com.joo.everyletter_back.file.controller;

import com.joo.everyletter_back.common.response.ApiSuccResp;
import com.joo.everyletter_back.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

   @PostMapping("/upload")
   public ApiSuccResp<String> uploadFile(@RequestParam("file") MultipartFile file) {
       return ApiSuccResp.from(fileService.fileUpload(file));
   }
}