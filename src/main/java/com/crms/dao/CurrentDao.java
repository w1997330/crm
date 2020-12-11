package com.crms.dao;

import com.crms.dto.PageDTO;
import com.crms.dto.RoleSearchDTO;
import com.crms.entity.Role;
import com.crms.vo.BootstrapTableVO;

public interface CurrentDao<T> {



    int  insert(T t);

    int update(T t);

    int delete(String uuid);

    Role selectByPrimaryKey(String uuid);
}
