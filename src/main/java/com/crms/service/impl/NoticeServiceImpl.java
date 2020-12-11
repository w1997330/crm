package com.crms.service.impl;

import com.crms.dao.NoticeDao;
import com.crms.dto.NoticeSearchDTO;
import com.crms.entity.Notice;
import com.crms.entity.Role;
import com.crms.service.NoticeService;
import com.crms.vo.BootstrapTableVO;
import com.crms.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeDao noticeDao;

    @Override
    public ResultVO search(NoticeSearchDTO pageDTO) {
        BootstrapTableVO bootstrapTableVO=this.noticeDao.search(pageDTO);
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("ok");
        resultVO.setData(bootstrapTableVO);

        return resultVO;
    }

    @Override
    public ResultVO insert(Notice obj) {
        int count=this.noticeDao.insert(obj);
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
    public ResultVO update(Notice obj) {
        int count=this.noticeDao.update(obj);
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
        int count=this.noticeDao.delete(uuid);
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
        Role role=this.noticeDao.selectByPrimaryKey(uuid);
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
