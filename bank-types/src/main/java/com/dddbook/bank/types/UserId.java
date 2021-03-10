package com.dddbook.bank.types;

public class UserId {
    //用户Id
    private final Long id;

    public Long getId(){
        return this.id;
    }

    public UserId(Long id) throws Exception {
        if (null == id){
            throw new Exception("用户id为空");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserId{" +
                "id=" + id +
                '}';
    }
}
