package com.test.demo.dao;

import com.test.demo.entity.EmployeeInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeInfDao {
    /**
     * 获取所有员工信息
     *
     * @return
     */
    List<EmployeeInf> queryEmployeeInfList();

    /**
     * 分页查询
     *
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<EmployeeInf> queryEmployeeInfPage(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    int queryEmployeeInfCount();

    /**
     * 有条件的查询
     *
     * @param employeeInfCondition
     * @return
     */
    List<EmployeeInf> queryEmployeeByCondition(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize, @Param("employeeInfCondition") EmployeeInf employeeInfCondition);

    int queryEmployeeConditionCount(@Param("employeeInfCondition") EmployeeInf employeeInfCondition);

    /**
     * 插入一条员工信息
     *
     * @param employeeInf
     * @return
     */
    int insertEmployeeInf(EmployeeInf employeeInf);

    /**
     * 更新相应员工信息
     *
     * @param employeeInf
     * @return
     */
    int updateEmployeeInf(EmployeeInf employeeInf);

    /**
     * 删除员工信息，根据Id进行批量删除
     *
     * @param employeeInf
     * @return
     */
    int deleteEmployeeInf(List<Long> ids);
}
