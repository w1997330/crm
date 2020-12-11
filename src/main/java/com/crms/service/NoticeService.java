package com.crms.service;

import com.crms.dto.NoticeSearchDTO;
import com.crms.dto.RoleSearchDTO;
import com.crms.entity.Notice;
import com.crms.entity.Role;
import com.crms.vo.ResultVO;

public interface NoticeService extends CurrentService<Notice> {
    ResultVO search(NoticeSearchDTO pageDTO);

    ResultVO  insert(Notice obj);

    ResultVO update(Notice obj);

    ResultVO delete(String uuid);

    ResultVO selectByPrimaryKey(String uuid);
}
