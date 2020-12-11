package com.crms.dao.impl;

import com.crms.dao.RoleDao;
import com.crms.dto.PageDTO;
import com.crms.dto.RoleSearchDTO;
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
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BootstrapTableVO search(RoleSearchDTO pageDTO) {
        BootstrapTableVO bootstrapTableVO=new BootstrapTableVO();
        List params=new ArrayList();
        StringBuffer sql=new StringBuffer("select * from sys_role where 1=1 ");
        //可能带有的条件
        if (StringUtils.isNotBlank(pageDTO.getRoleName())) {
            sql.append(" and role_name like ? ");
            params.add("%"+pageDTO.getRoleName()+"%");
        }
        //分页必须要有
        sql.append(" limit ?,? ");
        params.add(pageDTO.getOffset());
        params.add(pageDTO.getLimit());
        List<Role> arr=this.jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper<Role>(Role.class),params.toArray());
        bootstrapTableVO.setRows(arr);

        //查询总条数
        String sqlCount= SqlUtil.sqlCount(sql.toString());
        //移除分页参数
        params.remove(params.size()-1);
        params.remove(params.size()-1);

        this.jdbcTemplate.query(sqlCount, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                long count=resultSet.getLong(1);
                bootstrapTableVO.setTotal(count);
            }
        },params.toArray());
        return bootstrapTableVO;
    }

    @Override
    public int insert(Role obj) {
        String sql="insert into sys_role(uuid,role_name,role_remark) values(?,?,?)";
        List list=new ArrayList();
        list.add(obj.getUuid());
        list.add(obj.getRoleName());
        list.add(obj.getRoleRemark());

        int count=this.jdbcTemplate.update(sql,list.toArray());
        return count;
    }

    @Override
    public int update(Role obj) {
        String sql="update sys_role set role_name=?,role_remark=? where uuid=?";
        List list=new ArrayList();
        list.add(obj.getRoleName());
        list.add(obj.getRoleRemark());
        list.add(obj.getUuid());

        int count=this.jdbcTemplate.update(sql,list.toArray());
        return count;
    }

    @Override
    public int delete(String uuid) {
        String sql="delete from sys_role where uuid=?";
        int count=this.jdbcTemplate.update(sql,uuid);
        return count;
    }

    @Override
    public Role selectByPrimaryKey(String uuid) {
        String sql="select * from sys_role where uuid=?";
        Role role=new Role();

        role=this.jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Role>(Role.class),uuid);
        return role;
    }
}
