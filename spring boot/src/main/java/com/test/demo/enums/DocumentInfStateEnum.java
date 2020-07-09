package com.test.demo.enums;

import com.test.demo.entity.DocumentInf;

public enum DocumentInfStateEnum {
    OFFLINE(-1, "无效输入"), SUCCESS(0, "操作成功"), INNER_ERROR(-1001, "操作失败"), EMPTY(-1002, "没有权限");

    private int state;

    private String stateInfo;

    private DocumentInfStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static DocumentInfStateEnum stateOf(int index) {
        for (DocumentInfStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
