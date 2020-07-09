package com.test.demo.dto;

import com.test.demo.entity.NoticeInf;
import com.test.demo.enums.NoticeInfStateEnum;

import java.util.List;

public class NoticeInfExecution {
    private int state;

    private String stateInfo;

    private int count;

    private NoticeInf noticeInf;

    private List<NoticeInf> noticeInfList;

    public NoticeInfExecution() {

    }

    //失败的构造器
    public NoticeInfExecution(NoticeInfStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器
    public NoticeInfExecution(NoticeInfStateEnum stateEnum, NoticeInf noticeInf) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.noticeInf = noticeInf;
    }

    //成功的构造器
    public NoticeInfExecution(NoticeInfStateEnum stateEnum, List<NoticeInf> noticeInfList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.noticeInfList = noticeInfList;
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

    public NoticeInf getNoticeInf() {
        return noticeInf;
    }

    public void setNoticeInf(NoticeInf noticeInf) {
        this.noticeInf = noticeInf;
    }

    public List<NoticeInf> getNoticeInfList() {
        return noticeInfList;
    }

    public void setNoticeInfList(List<NoticeInf> noticeInfList) {
        this.noticeInfList = noticeInfList;
    }
}
