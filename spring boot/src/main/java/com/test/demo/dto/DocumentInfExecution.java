package com.test.demo.dto;

import com.test.demo.entity.DocumentInf;
import com.test.demo.enums.DocumentInfStateEnum;

import java.util.List;

public class DocumentInfExecution {
    private int state;

    private String stateInfo;

    private int count;

    private DocumentInf documentInf;

    private List<DocumentInf> documentInfList;

    public DocumentInfExecution() {

    }

    //失败的构造器
    public DocumentInfExecution(DocumentInfStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //成功的构造器
    public DocumentInfExecution(DocumentInfStateEnum stateEnum, DocumentInf documentInf) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.documentInf = documentInf;
    }

    //成功的构造器
    public DocumentInfExecution(DocumentInfStateEnum stateEnum, List<DocumentInf> documentInfList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.documentInfList = documentInfList;
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

    public DocumentInf getDocumentInf() {
        return documentInf;
    }

    public void setDocumentInf(DocumentInf documentInf) {
        this.documentInf = documentInf;
    }

    public List<DocumentInf> getDocumentInfList() {
        return documentInfList;
    }

    public void setDocumentInfList(List<DocumentInf> documentInfList) {
        this.documentInfList = documentInfList;
    }
}
