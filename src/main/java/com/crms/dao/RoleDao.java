package com.crms.dao;

import com.crms.dto.PageDTO;
import com.crms.dto.RoleSearchDTO;
import com.crms.entity.Role;
import com.crms.vo.BootstrapTableVO;

import java.util.List;

/**
 * @author nick
 */
public interface RoleDao extends CurrentDao<Role> {

    BootstrapTableVO search(RoleSearchDTO pageDTO);

    int  insert(Role obj);

    int update(Role obj);

    int delete(String uuid);

    Role selectByPrimaryKey(String uuid);
}
