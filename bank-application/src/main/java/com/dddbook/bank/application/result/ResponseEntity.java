package com.dddbook.bank.application.result;

import lombok.Data;

@Data
public class ResponseEntity<T> extends BaseResponse {
    public ResponseEntity() {
    }

    public ResponseEntity(T data) {
        this.data = data;
    }

    public ResponseEntity(ResultCode errorCode) {
        this.code = errorCode;
    }

    public ResponseEntity(ResultCode errorCode, String msg) {
        this.code = errorCode;
        this.message = msg;
    }

    private T data;
}
