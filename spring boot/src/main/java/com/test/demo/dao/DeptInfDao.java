package com.test.demo.dao;

import com.test.demo.entity.DeptInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptInfDao {

    /**
     * 查询所有信息
     *
     * @return
     */
    List<DeptInf> deptInfList();

    /**
     * 查询所有信息分页
     *
     * @return
     */
    List<DeptInf> queryDeptInfList(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);


    int queryDeptInfCount();

    /**
     * 按条件查询
     *
     * @param DeptInfCondition
     * @return
     */
    List<DeptInf> queryDeptInfById(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize, @Param("DeptInfCondition") DeptInf DeptInfCondition);


    int queryDeptConditionCount(@Param("DeptInfCondition") DeptInf DeptInfCondition);

    /**
     * 插入一条记录
     *
     * @return
     */
    int insertDeptInf(DeptInf deptInf);


    /***
     * 更新信息
     * @param deptInf
     * @return
     */
    int updateDeptInf(DeptInf deptInf);

    /**
     * 根据ID进行批量删除
     *
     * @param ids
     * @return
     */
    int deleteDeptInf(List<Long> ids);
}
