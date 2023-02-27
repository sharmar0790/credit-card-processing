package com.cc.model;

public class Status {

    private final String status;
    private final String mesage;
    private final int code;

    public String getStatus() {
        return status;
    }

    public String getMesage() {
        return mesage;
    }

    public int getCode() {
        return code;
    }

    public Status(String status, String mesage, int code) {
        this.status = status;
        this.mesage = mesage;
        this.code = code;
    }
}
