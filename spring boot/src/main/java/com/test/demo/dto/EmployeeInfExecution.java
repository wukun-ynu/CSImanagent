package com.test.demo.dto;

import com.test.demo.entity.EmployeeInf;
import com.test.demo.enums.EmployeeInfStateEnum;

import java.util.List;

public class EmployeeInfExecution {
    private int state;

    private String stateInfo;

    private int count;

    private EmployeeInf employeeInf;

    private List<EmployeeInf> employeeInfList;

    public EmployeeInfExecution() {

    }

    //失败的构造器
    public EmployeeInfExecution(EmployeeInfStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器
    public EmployeeInfExecution(EmployeeInfStateEnum stateEnum, EmployeeInf employeeInf) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.employeeInf = employeeInf;
    }

    //成功的构造器
    public EmployeeInfExecution(EmployeeInfStateEnum stateEnum, List<EmployeeInf> employeeInfList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.employeeInfList = employeeInfList;
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

    public EmployeeInf getEmployeeInf() {
        return employeeInf;
    }

    public void setEmployeeInf(EmployeeInf employeeInf) {
        this.employeeInf = employeeInf;
    }

    public List<EmployeeInf> getEmployeeInfList() {
        return employeeInfList;
    }

    public void setEmployeeInfList(List<EmployeeInf> employeeInfList) {
        this.employeeInfList = employeeInfList;
    }
}
