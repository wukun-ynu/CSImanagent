package com.test.demo.dto;

import com.test.demo.entity.JobInf;
import com.test.demo.enums.JobInfStateEnum;

import java.util.List;

public class JobInfExecution {
    private int state;

    private String stateInfo;

    private int count;

    private JobInf jobInf;

    private List<JobInf> jobInfList;

    public JobInfExecution() {

    }

    //失败的构造器
    public JobInfExecution(JobInfStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器
    public JobInfExecution(JobInfStateEnum stateEnum, JobInf jobInf) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.jobInf = jobInf;
    }

    //成功的构造器
    public JobInfExecution(JobInfStateEnum stateEnum, List<JobInf> jobInfList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.jobInfList = jobInfList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public JobInf getJobInf() {
        return jobInf;
    }

    public void setJobInf(JobInf jobInf) {
        this.jobInf = jobInf;
    }

    public List<JobInf> getJobInfList() {
        return jobInfList;
    }

    public void setJobInfList(List<JobInf> jobInfList) {
        this.jobInfList = jobInfList;
    }
}
