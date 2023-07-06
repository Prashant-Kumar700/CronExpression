package com.example.cron.Parser;

import com.example.cron.Constants.DAYOFWEEK;

import static com.example.cron.Constants.Constants.*;

/*
* Class to parse DayOfWeek.
 */
public class DayOfWeekBaseParser extends BaseParser {

    static int START_DAY = 0;
    static int END_DAY = 6;
    public DayOfWeekBaseParser(String parseString) {
        super(parseString);
        if(isSpecialCharacterPresent(SLASH)) {
            setStep();
        }
        setRange(START_DAY,END_DAY);
        setOccurrences();
    }

    /*
    *
    * Overrides the base class setRange function to take care of alphabets(MON,TUE) instead of numbers.
     */
    @Override
    void setRange(int start, int end) {
        if(isSpecialCharacterPresent(DASH)) {
            String[] stepRange = super.parseString.split(DASH);
            if(stepRange[0].matches("\\d+")) {
                parserParams.setStart(Integer.valueOf(stepRange[0]));
                parserParams.setEnd(Integer.valueOf(stepRange[1]));
            } else {
                Boolean startSet=false, endSet= false;
                for (DAYOFWEEK day : DAYOFWEEK.values()) {
                    if (day.name().equalsIgnoreCase(stepRange[0])) {
                        parserParams.setStart(day.getValue());
                        startSet = true;
                    }
                    if(day.name().equalsIgnoreCase(stepRange[1])) {
                        parserParams.setEnd(day.getValue());
                        endSet = true;
                    }
                }
                if(!(startSet && endSet)) throw new IllegalArgumentException("Argument day not valid");
            }
        } else if(isSpecialCharacterPresent(STAR)){
            if(!isValidStar()) {
                throw new IllegalArgumentException("Argument day not valid");
            } else {
                parserParams.setStart(START_DAY);
                parserParams.setEnd(END_DAY);
            }
        } else if(!isSpecialCharacterPresent(COMMA)) {
            if(parseString.matches("\\d+")) {
                parserParams.setStart(Integer.valueOf(parseString));
                parserParams.setEnd(isSpecialCharacterPresent(SLASH) ? END_DAY:parserParams.getStart());
            } else {
                Boolean set=false;
                for (DAYOFWEEK day : DAYOFWEEK.values()) {
                    if (day.name().equalsIgnoreCase(parseString)) {
                        parserParams.setStart(day.getValue());
                        parserParams.setEnd(isSpecialCharacterPresent(SLASH) ? END_DAY:parserParams.getStart());
                        set = true;
                        break;
                    }
                }
                if(!set) throw new IllegalArgumentException("Argument day not valid");
            }
        }
        validateRange(start,end,parserParams.getStart(),parserParams.getEnd());
    }

    @Override
    public String toString() {
        return "day of week   "+super.toString();
    }
}
