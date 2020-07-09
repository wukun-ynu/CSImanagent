package com.test.demo.dao;

import com.test.demo.entity.JobInf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobInfDao {
    /**
     * 查询所有信息
     *
     * @return
     */
    List<JobInf> jobInfList();

    /**
     * 查询所有职位信息 分页
     *
     * @return
     */
    List<JobInf> queryJobInfList(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    int queryJobInfCount();

    /**
     * 根据搜索条件查询
     *
     * @param jobInfCondition
     * @return
     */
    List<JobInf> queryJobInfById(@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize, @Param("jobInfCondition") JobInf jobInfCondition);


    int queryUserInfConditionCount(@Param("jobInfCondition") JobInf jobInfCondition);

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

    /***
     * 根据Id进行批量删除
     * @param ids
     * @return
     */
    int deleteJobInf(List<Long> ids);
}
