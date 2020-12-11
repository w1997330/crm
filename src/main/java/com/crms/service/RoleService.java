package com.crms.service;

import com.crms.dao.CurrentDao;
import com.crms.dto.PageDTO;
import com.crms.dto.RoleSearchDTO;
import com.crms.entity.Role;
import com.crms.vo.ResultVO;


public interface RoleService extends CurrentService<Role> {

    ResultVO search(RoleSearchDTO pageDTO);

    ResultVO  insert(Role obj);

    ResultVO update(Role obj);

    ResultVO delete(String uuid);

    ResultVO selectByPrimaryKey(String uuid);
}
