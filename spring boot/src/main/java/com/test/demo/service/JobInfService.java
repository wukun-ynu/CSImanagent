package com.test.demo.service;

import com.test.demo.dto.JobInfExecution;
import com.test.demo.entity.JobInf;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.List;

public interface JobInfService {

    List<JobInf> jobInfList();

    /**
     * 获取所有职位信息 分页
     *
     * @return
     */
    JobInfExecution getJobInfList(int pageIndex, int pageSize);

    /**
     * 获取查询职位信息
     *
     * @param jobInfCondition
     * @return
     */
    JobInfExecution getJobInf(int pageIndex, int pageSize, JobInf jobInfCondition);

    /**
     * 插入一条职位信息
     *
     * @param jobInf
     * @return
     */
    int insertJobInf(JobInf jobInf);

    /**
     * 更新职位信息
     *
     * @param jobInf
     * @return
     */
    int updateJobInf(JobInf jobInf);

    /**
     * 根据Id进行批量删除
     *
     * @param jobInf
     * @return
     */
    int deleteJobInf(List<Long> ids);
}
