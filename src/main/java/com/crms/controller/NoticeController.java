package com.crms.controller;


import com.crms.dto.NoticeSearchDTO;
import com.crms.service.NoticeService;
import com.crms.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping(value = "/api/notice/data")
    public ResultVO search(NoticeSearchDTO noticeSearchDTO){
        return this.noticeService.search(noticeSearchDTO);
    }
}
