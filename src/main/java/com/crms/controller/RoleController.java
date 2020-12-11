package com.crms.controller;

import com.crms.dto.PageDTO;
import com.crms.dto.RoleSearchDTO;
import com.crms.entity.Role;
import com.crms.service.RoleService;
import com.crms.util.UUIDUtil;
import com.crms.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    //查询
    @GetMapping(value = "/api/role/data")
    public ResultVO search(RoleSearchDTO pageDTO) {
        return this.roleService.search(pageDTO);
    };

    //单查
    @GetMapping(value = "/api/role/findone")
    public ResultVO findOne (String uuid) {
        return this.roleService.selectByPrimaryKey(uuid);
    }

    @PostMapping(value = "/api/role/add")
    public ResultVO insert(Role role) {

        role.setUuid(UUIDUtil.createUUID());
        return this.roleService.insert(role);
    }


}
