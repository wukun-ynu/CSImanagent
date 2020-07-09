package com.test.demo.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.demo.dao.JobInfDao;
import com.test.demo.dto.JobInfExecution;
import com.test.demo.entity.JobInf;
import com.test.demo.enums.JobInfStateEnum;
import com.test.demo.service.JobInfService;
import com.test.demo.until.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class JobInfServiceImpl implements JobInfService {

    @Resource
    private JobInfDao jobInfDao;

    @Override
    public List<JobInf> jobInfList() {
        return jobInfDao.jobInfList();
    }

    @Override
    public JobInfExecution getJobInfList(int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<JobInf> jobInfList = jobInfDao.queryJobInfList(rowIndex, pageSize);
        int count = jobInfDao.queryJobInfCount();
        JobInfExecution ue = new JobInfExecution();
        if (jobInfList != null) {
            ue.setJobInfList(jobInfList);
            ue.setCount(count);
            ue.setState(JobInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(JobInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(JobInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    public JobInfExecution getJobInf(int pageIndex, int pageSize, JobInf jobInfCondition) {
        int rowIndex = PageCalculator.calculatePowIndex(pageIndex, pageSize);
        List<JobInf> jobInfList = jobInfDao.queryJobInfById(rowIndex, pageSize, jobInfCondition);
        int count = jobInfDao.queryUserInfConditionCount(jobInfCondition);
        JobInfExecution ue = new JobInfExecution();
        if (jobInfList != null) {
            ue.setJobInfList(jobInfList);
            ue.setCount(count);
            ue.setState(JobInfStateEnum.SUCCESS.getState());
            ue.setStateInfo(JobInfStateEnum.SUCCESS.getStateInfo());
        } else {
            ue.setState(JobInfStateEnum.INNER_ERROR.getState());
        }
        return ue;
    }

    @Override
    public int insertJobInf(JobInf jobInf) {
        return jobInfDao.insertJobInf(jobInf);
    }

    @Override
    public int updateJobInf(JobInf jobInf) {
        return jobInfDao.updateJobInf(jobInf);
    }

    @Override
    public int deleteJobInf(List<Long> ids) {
        return jobInfDao.deleteJobInf(ids);
    }
}
