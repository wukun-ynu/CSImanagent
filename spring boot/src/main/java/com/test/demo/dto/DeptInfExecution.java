package com.test.demo.dto;

import com.test.demo.entity.DeptInf;
import com.test.demo.enums.DeptInfStateEnum;
import com.test.demo.enums.UserInfStateEnum;

import java.util.List;

public class DeptInfExecution {
    private int state;

    private String stateInfo;

    private int count;

    private DeptInf deptInf;

    private List<DeptInf> deptInfList;

    public DeptInfExecution() {

    }

    //失败的构造器
    public DeptInfExecution(DeptInfStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器
    public DeptInfExecution(DeptInfStateEnum stateEnum, DeptInf deptInf) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.deptInf = deptInf;
    }

    //成功的构造器
    public DeptInfExecution(DeptInfStateEnum stateEnum, List<DeptInf> deptInfList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.deptInfList = deptInfList;
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

    public DeptInf getDeptInf() {
        return deptInf;
    }

    public void setDeptInf(DeptInf deptInf) {
        this.deptInf = deptInf;
    }

    public List<DeptInf> getDeptInfList() {
        return deptInfList;
    }

    public void setDeptInfList(List<DeptInf> deptInfList) {
        this.deptInfList = deptInfList;
    }
}
