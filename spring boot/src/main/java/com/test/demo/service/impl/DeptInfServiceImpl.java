package com.test.demo.service.impl;

import com.test.demo.dao.DeptInfDao;
import com.test.demo.dto.DeptInfExecution;
import com.test.demo.entity.DeptInf;
import com.test.demo.entity.JobInf;
import com.test.demo.enums.DeptInfStateEnum;
import com.test.demo.service.DeptInfService;
import com.test.demo.until.PageCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptInfServiceImpl implements DeptInfService {
    @Resource
    private DeptInfDao deptInfDao;

    @Override
    public List<DeptInf> deptInfList() {
        return deptInfDao.deptInfList();
    }

    @Override
    public DeptInfExecution queryDeptInfList(int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<DeptInf> deptInfList = deptInfDao.queryDeptInfList(rowIndex, pageSize);
        int count = deptInfDao.queryDeptInfCount();
        DeptInfExecution ue = new DeptInfExecution();
        if (deptInfList != null) {
            ue.setDeptInfList(deptInfList);
            ue.setCount(count);
            ue.setState(DeptInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(DeptInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(DeptInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    public DeptInfExecution queryDeptInf(int pageIndex, int pageSize, DeptInf deptInfCondition) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<DeptInf> deptInfList = deptInfDao.queryDeptInfById(rowIndex, pageSize, deptInfCondition);
        int count = deptInfDao.queryDeptConditionCount(deptInfCondition);
        DeptInfExecution ue = new DeptInfExecution();
        if (deptInfList != null) {
            ue.setDeptInfList(deptInfList);
            ue.setCount(count);
            ue.setState(DeptInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(DeptInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(DeptInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    @Transactional
    public int insertDeptInf(DeptInf deptInf) {
        return deptInfDao.insertDeptInf(deptInf);
    }

    @Override
    @Transactional
    public int updateDeptInf(DeptInf deptInf) {
        return deptInfDao.updateDeptInf(deptInf);
    }

    @Override
    @Transactional
    public int deleteDeptInf(List<Long> ids) {
        return deptInfDao.deleteDeptInf(ids);
    }
}
