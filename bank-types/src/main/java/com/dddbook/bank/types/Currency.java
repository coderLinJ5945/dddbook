package com.dddbook.bank.types;

/**
 * 币种
 */
public enum Currency {
    CNY(1, "人民币"),
    UN(2, "人民币"),
    EN(3, "法币");

    private final int id;
    private final String currency;

    public int getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    Currency(int id, String currency) {
        this.id = id;
        this.currency = currency;
    }

    public static Currency getById(int id) {
        for (Currency ot : values()) {
            if (ot.id == id) {
                return ot;
            }
        }
        return null;
    }
}
