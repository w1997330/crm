package com.crms.dao;

import com.crms.dto.NoticeSearchDTO;
import com.crms.dto.RoleSearchDTO;
import com.crms.entity.Notice;
import com.crms.entity.Role;
import com.crms.vo.BootstrapTableVO;

public interface NoticeDao extends CurrentDao<Notice>{
    BootstrapTableVO search(NoticeSearchDTO pageDTO);

    int  insert(Notice obj);

    int update(Notice obj);

    int delete(String uuid);

    Role selectByPrimaryKey(String uuid);
}
