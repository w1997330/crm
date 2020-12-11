package com.crms.service;

import com.crms.entity.Role;
import com.crms.vo.ResultVO;

public interface CurrentService<T> {
    ResultVO insert(T t);


    ResultVO update(T t);

    ResultVO delete(String uuid);

    ResultVO selectByPrimaryKey(String uuid);
}
