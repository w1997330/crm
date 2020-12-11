package com.crms.service.impl;

import com.crms.dao.RoleDao;
import com.crms.dao.impl.RoleDaoImpl;
import com.crms.entity.Role;
import com.crms.service.RoleService;
import com.crms.vo.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RoleServiceTestImpl {

    @Autowired
    private RoleService roleService;

    @Test
    public void search(){

        Role obj=new Role();
        obj.setUuid("123123");
        obj.setRoleName("111");
        obj.setRoleRemark("123");

        ResultVO search = roleService.insert(obj);


    }
}
