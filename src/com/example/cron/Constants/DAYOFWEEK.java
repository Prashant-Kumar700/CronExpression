package com.example.cron.Constants;

public enum DAYOFWEEK {
    SUN(0), MON(1), TUE(2), WED(3), THU(4), FRI(5), SAT(6);

    private final int value;
    DAYOFWEEK(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
