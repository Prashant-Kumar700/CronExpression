package com.example.cron;

public class CronExpression {
    public static void main(String[] args) {
        String cronCommand = args[0];
        System.out.println(args[0]);
        CronParser cronParser = new CronParser(cronCommand);
        System.out.println(cronParser);
    }
}