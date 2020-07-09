package com.test.demo.dto;

import com.test.demo.entity.UserInf;
import com.test.demo.enums.UserInfStateEnum;

import java.util.List;

public class UserInfExecution {
    private int state;

    private String stateInfo;

    private int count;

    private UserInf userInf;

    private List<UserInf> userInfList;

    public UserInfExecution() {

    }

    //失败的构造器
    public UserInfExecution(UserInfStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器
    public UserInfExecution(UserInfStateEnum stateEnum, UserInf usrInf) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.userInf = usrInf;
    }

    //成功的构造器
    public UserInfExecution(UserInfStateEnum stateEnum, List<UserInf> usrInfList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.userInfList = usrInfList;
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

    public UserInf getUserInf() {
        return userInf;
    }

    public void setUserInf(UserInf userInf) {
        this.userInf = userInf;
    }

    public List<UserInf> getUserInfList() {
        return userInfList;
    }

    public void setUserInfList(List<UserInf> userInfList) {
        this.userInfList = userInfList;
    }
}
