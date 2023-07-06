package com.example.cron.Parser;

import static com.example.cron.Constants.Constants.*;

/*
*
* Class to parse dayOfMonth.
 */
public class DayOfMonthBaseParser extends BaseParser {

    static int START_DAY = 0;
    static int END_DAY = 31;
    public DayOfMonthBaseParser(String parseString) {
        super(parseString);
        if(isSpecialCharacterPresent(SLASH)) {
            setStep();
        }
        setRange(START_DAY,END_DAY);
        setOccurrences();
    }

    @Override
    public String toString() {
        return "day of month  "+super.toString();
    }
}
