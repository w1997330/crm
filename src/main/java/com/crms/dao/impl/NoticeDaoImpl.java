package com.crms.dao.impl;

import com.crms.dao.NoticeDao;
import com.crms.dto.NoticeSearchDTO;
import com.crms.dto.PageDTO;
import com.crms.dto.RoleSearchDTO;
import com.crms.entity.Notice;
import com.crms.entity.Role;
import com.crms.util.SqlUtil;
import com.crms.vo.BootstrapTableVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BootstrapTableVO search(NoticeSearchDTO pageDTO) {
        BootstrapTableVO bootstrapTableVO=new BootstrapTableVO();
        List params=new ArrayList();
        StringBuffer sql=new StringBuffer("select * from sys_notice where 1=1 ");
        if (StringUtils.isNotBlank(pageDTO.getUserName())){
            sql.append(" and user_name like ? ");
            params.add("%"+ pageDTO.getUserName() +"%");
        }
        sql.append(" limit ?,? ");
        params.add(pageDTO.getOffset());
        params.add(pageDTO.getLimit());
        List<Notice> arr=this.jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper<Notice>(Notice.class),params.toArray());
        bootstrapTableVO.setRows(arr);
        //查询总条数
        String sqlCount= SqlUtil.sqlCount(sql.toString());
        //移除分页参数
        params.remove(params.size()-1);
        params.remove(params.size()-1);
        this.jdbcTemplate.query(sqlCount, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                long count=rs.getLong(1);
                bootstrapTableVO.setTotal(count);
            }
        },params.toArray());
        return bootstrapTableVO;
    }

    @Override
    public int insert(Notice notice) {
        String sql="insert into sys_notice(user_uuid,user_name,notice_title,notice_content,create_time) values(?,?,?,?,?) ";
        List list=new ArrayList();
        list.add(notice.getUserUuid());
        list.add(notice.getUserName());
        list.add(notice.getNoticeTitle());
        list.add(notice.getNoticeContent());
        list.add(notice.getCreateTime());
        int count=this.jdbcTemplate.update(sql,list.toArray());
        return count;
    }

    @Override
    public int update(Notice notice) {
        String sql="update sys_notice set user_name=?,notice_title=?,notice_content=?,create_time=? where user_uuid=?";
        List list=new ArrayList();
        list.add(notice.getUserName());
        list.add(notice.getNoticeTitle());
        list.add(notice.getNoticeContent());
        list.add(notice.getCreateTime());
        list.add(notice.getUserUuid());
        int count=this.jdbcTemplate.update(sql,list.toArray());
        return count;
    }

    @Override
    public int delete(String uuid) {
        String sql="delete from sys_notice where user_uuid=? ";
        int count=this.jdbcTemplate.update(sql,uuid);
        return count;
    }

    @Override
    public Role selectByPrimaryKey(String uuid) {
        return null;
    }
}
