package com.bonaparte.bean;

public class SocketMessage {
    private String userName;
    private Integer point;
    private String targetName;
    private String ifEnd;

    public String getIfEnd() {
        return ifEnd;
    }

    public void setIfEnd(String ifEnd) {
        this.ifEnd = ifEnd;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
