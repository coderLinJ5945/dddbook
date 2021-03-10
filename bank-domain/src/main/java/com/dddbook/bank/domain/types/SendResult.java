package com.dddbook.bank.domain.types;

public class SendResult {
    private String result;
    private Integer code = 200;

    public SendResult() {
    }

    public SendResult(Integer code) {
        this.code = code;
    }

    public SendResult(String result) {
        this.result = result;
    }

    public SendResult(String result, Integer code) {
        this.result = result;
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
