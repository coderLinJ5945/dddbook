package com.dddbook.bank.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AccountNumber {
    //账户Id
    private final String number;

    public String getNumber(){
        return this.number;
    }

    public AccountNumber(String number) throws Exception {
        if (number.isEmpty()){
            throw new Exception("账户号为空");
        }
        this.number = number;
    }

    @Override
    public String toString() {
        return "AccountNumber{" +
                "number='" + number + '\'' +
                '}';
    }
}
