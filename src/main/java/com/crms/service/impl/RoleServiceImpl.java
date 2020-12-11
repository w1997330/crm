package com.crms.service.impl;

import com.crms.dao.RoleDao;
import com.crms.dto.PageDTO;
import com.crms.dto.RoleSearchDTO;
import com.crms.entity.Role;
import com.crms.service.RoleService;
import com.crms.vo.BootstrapTableVO;
import com.crms.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public ResultVO search(RoleSearchDTO pageDTO) {
        BootstrapTableVO bootstrapTableVO=this.roleDao.search(pageDTO);
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("ok");
        resultVO.setData(bootstrapTableVO);

        return resultVO;
    }

    @Override
    public ResultVO insert(Role obj) {
        int count=this.roleDao.insert(obj);
        System.out.println(count);
        ResultVO resultVO=new ResultVO();
        if (count>0) {
            resultVO.setCode(0);
            resultVO.setMessage("ok");
        } else {
            resultVO.setCode(500);
            resultVO.setMessage("新增数据失败");
        }
        return resultVO;
    }

    @Override
    public ResultVO update(Role obj) {
        int count=this.roleDao.update(obj);
        ResultVO resultVO=new ResultVO();
        if (count>0) {
            resultVO.setCode(0);
            resultVO.setMessage("ok");
        } else {
            resultVO.setCode(500);
            resultVO.setMessage("更新数据失败");
        }
        return resultVO;
    }

    @Override
    public ResultVO delete(String uuid) {
        int count=this.roleDao.delete(uuid);
        ResultVO resultVO=new ResultVO();
        if (count>0) {
            resultVO.setCode(0);
            resultVO.setMessage("ok");
        } else {
            resultVO.setCode(500);
            resultVO.setMessage("删除数据失败");
        }
        return resultVO;
    }

    @Override
    public ResultVO selectByPrimaryKey(String uuid) {
        Role role=this.roleDao.selectByPrimaryKey(uuid);
        ResultVO resultVO=new ResultVO();

        if (role!=null) {
            resultVO.setCode(0);
            resultVO.setMessage("ok");
            resultVO.setData(role);
        } else {
            resultVO.setCode(404);
            resultVO.setMessage("查询不到该数据");
        }
        return resultVO;
    }
}
