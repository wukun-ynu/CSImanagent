package com.test.demo.service.impl;


import com.test.demo.dao.EmployeeInfDao;
import com.test.demo.dto.EmployeeInfExecution;
import com.test.demo.entity.EmployeeInf;
import com.test.demo.enums.EmployeeInfStateEnum;
import com.test.demo.service.EmployeeInfService;
import com.test.demo.until.PageCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeInfServiceImpl implements EmployeeInfService {
    @Resource
    private EmployeeInfDao employeeInfDao;

    @Override
    public List<EmployeeInf> getEmployeeInfList() {
        return employeeInfDao.queryEmployeeInfList();
    }

    @Override
    public EmployeeInfExecution queryEmployeeInfPage(int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<EmployeeInf> employeeInfList = employeeInfDao.queryEmployeeInfPage(rowIndex, pageSize);
        int count = employeeInfDao.queryEmployeeInfCount();
        EmployeeInfExecution ue = new EmployeeInfExecution();
        if (employeeInfList != null) {
            ue.setEmployeeInfList(employeeInfList);
            ue.setCount(count);
            ue.setState(EmployeeInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(EmployeeInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(EmployeeInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    public EmployeeInfExecution getEmployeeInf(int pageIndex, int pageSize, EmployeeInf employeeInfCondition) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<EmployeeInf> employeeInfList = employeeInfDao.queryEmployeeByCondition(rowIndex, pageSize, employeeInfCondition);
        int count = employeeInfDao.queryEmployeeConditionCount(employeeInfCondition);
        EmployeeInfExecution ue = new EmployeeInfExecution();
        if (employeeInfList != null) {
            ue.setEmployeeInfList(employeeInfList);
            ue.setCount(count);
            ue.setState(EmployeeInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(EmployeeInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(EmployeeInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    @Transactional
    public int insertEmploeeInf(EmployeeInf employeeInf) {
        return employeeInfDao.insertEmployeeInf(employeeInf);
    }

    @Override
    @Transactional
    public int updateEmployeeInf(EmployeeInf employeeInf) {
        return employeeInfDao.updateEmployeeInf(employeeInf);
    }

    @Override
    @Transactional
    public int deleteEmployeeInf(List<Long> ids) {
        return employeeInfDao.deleteEmployeeInf(ids);
    }
}
