package com.example.cron.Constants;

public enum MONTH {
    JAN(1), FEB(2), MAR(3), APR(4), MAY(5), JUN(6), JUL(7), AUG(8), SEP(9), OCT(10), NOV(11), DEC(12);

    private final int value;
    MONTH(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
