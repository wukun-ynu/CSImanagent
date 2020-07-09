package com.test.demo.service;

import com.test.demo.dto.UserInfExecution;
import com.test.demo.entity.UserInf;

import java.util.List;

public interface UserInfService {

    /**
     * 获取分页数
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    UserInfExecution getUserListByPage(int pageIndex, int pageSize);

    /**
     * 获取所有用户信息
     *
     * @return
     */
    List<UserInf> getUserList();

    /**
     * 按筛选条件进行查询
     *
     * @param userInfCondition
     * @return
     */
    UserInfExecution getUserInfList(int pageIndex, int pageSize, UserInf userInfCondition);

    /**
     * 添加用户
     *
     * @param userInf
     * @return
     */
    int addUser(UserInf userInf);

    /**
     * 用户信息更新
     *
     * @param userInf
     * @return
     */
    int updateUser(UserInf userInf);

    /**
     * 用户批量删除
     *
     * @param ids
     * @return
     */
    int deleteUsers(List<Long> ids);

    UserInf findByUserName(String userName);
}
