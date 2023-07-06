package com.example.cron.Parser;

import static com.example.cron.Constants.Constants.*;

/*
 *
 * Class to parse Minute.
 */
public class MinuteBaseParser extends BaseParser {

    private static final int START_MINUTE = 0;
    private static final int END_MINUTE = 59;
    public MinuteBaseParser(String parseString) {
        super(parseString);
        if(isSpecialCharacterPresent(SLASH)) {
            setStep();
        }
        setRange(START_MINUTE,END_MINUTE);
        setOccurrences();
    }

    @Override
    public String toString() {
        return "minute        "+super.toString();
    }
}
