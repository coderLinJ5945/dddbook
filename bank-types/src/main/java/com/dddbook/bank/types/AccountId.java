package com.dddbook.bank.types;

public class AccountId{
    //账户Id
    private final Long id;

    public Long getId(){
        return this.id;
    }

    public AccountId(Long id) throws Exception {
        if (null == id){
            throw new Exception("账户id为空");
        }else if (0 >= id){
            throw new Exception("账户id非法");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "AccountId{" +
                "id=" + id +
                '}';
    }
}
