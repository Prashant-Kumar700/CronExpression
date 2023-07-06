package com.example.cron;

import com.example.cron.Parser.*;

/*
* Aggregator class for all the parser. Aggregates minutes, hours, dayOfMonth, dayOfWeek, month parsers.
*/
public class CronParser {
    String[] cronParams;

    BaseParser minuteParser;
    BaseParser hourParser;
    BaseParser dayOfMonthParser;
    BaseParser monthParser;
    BaseParser dayOfWeekParser;
    String comment;

    public CronParser(String cronExpression) {
        cronParams = cronExpression.split(" ");
        validateNumberOfParams(cronParams);
        minuteParser = new MinuteBaseParser(cronParams[0]);
        hourParser = new HourBaseParser(cronParams[1]);
        dayOfMonthParser = new DayOfMonthBaseParser(cronParams[2]);
        monthParser = new MonthBaseParser(cronParams[3]);
        dayOfWeekParser = new DayOfWeekBaseParser(cronParams[4]);
        comment = cronParams[5];
    }

    /*
    ** Validate if the number of params are appropriate or not.
    */
    private void validateNumberOfParams(String[] splitExpression) {
        if(splitExpression.length!=6) {
            throw new AssertionError("Invalid cron expression");
        }
    }

    @Override
    public String toString() {
        return minuteParser+"\n"
                +hourParser+"\n"
                +dayOfMonthParser+"\n"
                +monthParser+"\n"
                +dayOfWeekParser+"\n"
                +"command       "+cronParams[5];
    }
}
