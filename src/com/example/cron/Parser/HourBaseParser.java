package com.example.cron.Parser;

import static com.example.cron.Constants.Constants.*;

/*
 *
 * Class to parse Hour.
 */
public class HourBaseParser extends BaseParser {

    static int START_HOUR = 0;
    static int END_HOUR = 23;

    public HourBaseParser(String parseString) {
        super(parseString);
        if(isSpecialCharacterPresent(SLASH)) {
            setStep();
        }
        setRange(START_HOUR,END_HOUR);
        setOccurrences();
    }

    @Override
    public String toString() {
        return "hour          "+super.toString();
    }
}