package com.test.demo.dao;

import com.test.demo.entity.UserInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfDao {

    /**
     * 分页处理
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<UserInf> queryUserInfPage(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 没条件的所有信息
     *
     * @return
     */
    int queryUserInfCount();

    /**
     * @return
     */
    List<UserInf> getUserList();

    /**
     * 查询有条件的页数
     *
     * @param rowIndex
     * @param pageSize
     * @param userInfCondition
     * @return
     */
    List<UserInf> queryUserInfList(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize,
                                   @Param("userInfCondition") UserInf userInfCondition);


    /**
     * 查询有条件的总数
     *
     * @return
     */
    int queryUserInfConditionCount(@Param("userInfCondition") UserInf userInfCondition);

    /**
     * @param UserInf userInf
     * @return
     */
    int insertUserInf(UserInf userInf);

    /**
     * 更新用户信息
     *
     * @param userInf
     * @return
     */
    int updateUserInf(UserInf userInf);

    /**
     * 根据提供的Id进行删除
     *
     * @param ids
     * @return
     */
    int deleteUserInf(List<Long> ids);

    /**
     * 根据用户查询
     *
     * @param userName
     * @return
     */
    UserInf findByUserName(String userName);
}
