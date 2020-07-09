package com.test.demo.service;

import com.test.demo.dto.EmployeeInfExecution;
import com.test.demo.entity.EmployeeInf;

import java.util.EmptyStackException;
import java.util.List;

public interface EmployeeInfService {
    /**
     * 获取所有员工信息
     *
     * @return
     */
    List<EmployeeInf> getEmployeeInfList();

    /**
     * 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    EmployeeInfExecution queryEmployeeInfPage(int pageIndex, int pageSize);

    /**
     * 根据查询条件查询员工信息
     *
     * @param employeeInfCondition
     * @return
     */
    EmployeeInfExecution getEmployeeInf(int pageIndex, int pageSize, EmployeeInf employeeInfCondition);

    /**
     * 插入一条员工信息
     *
     * @param employeeInf
     * @return
     */
    int insertEmploeeInf(EmployeeInf employeeInf);

    /**
     * 更新员工信息
     *
     * @param employeeInf
     * @return
     */
    int updateEmployeeInf(EmployeeInf employeeInf);

    /**
     * 根据Id进行批量删除
     *
     * @param employeeInf
     * @return
     */
    int deleteEmployeeInf(List<Long> ids);
}
