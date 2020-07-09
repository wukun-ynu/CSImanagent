package com.test.demo.service;

import com.test.demo.dto.DeptInfExecution;
import com.test.demo.entity.DeptInf;

import java.util.List;

public interface DeptInfService {

    List<DeptInf> deptInfList();

    /**
     * 查询所有信息 分页
     *
     * @return
     */
    public DeptInfExecution queryDeptInfList(int pageIndex, int pageSize);

    /**
     * 有条件的查询
     *
     * @return
     * @Param deptInfCondition
     */
    public DeptInfExecution queryDeptInf(int pageIndex, int pageSize, DeptInf deptInfCondition);

    /**
     * 插入一条记录
     *
     * @param deptInf
     * @return
     */
    public int insertDeptInf(DeptInf deptInf);

    /**
     * 更新一条记录
     *
     * @param deptInf
     * @return
     */
    public int updateDeptInf(DeptInf deptInf);

    /**
     * 根据Id进行批量删除
     *
     * @param ids
     * @return
     */
    public int deleteDeptInf(List<Long> ids);

}
