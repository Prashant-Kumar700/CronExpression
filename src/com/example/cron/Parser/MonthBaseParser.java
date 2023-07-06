package com.example.cron.Parser;

import static com.example.cron.Constants.Constants.*;

import com.example.cron.Constants.MONTH;

/*
 *
 * Class to parse month.
 */
public class MonthBaseParser extends BaseParser {

    static int START_MONTH = 1;
    static int END_MONTH = 12;
    public MonthBaseParser(String parseString) {
        super(parseString);
        if(isSpecialCharacterPresent(SLASH)) {
            setStep();
        }
        setRange(START_MONTH,END_MONTH);
        setOccurrences();
    }

    /*
     *
     * Overrides the base class setRange function to take care of alphabets(JAN,FEB) instead of numbers.
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
                for (MONTH month : MONTH.values()) {
                    if (month.name().equalsIgnoreCase(stepRange[0])) {
                        parserParams.setStart(month.getValue());
                        startSet = true;
                    }
                    if(month.name().equalsIgnoreCase(stepRange[1])) {
                        parserParams.setEnd(month.getValue());
                        endSet = true;
                    }
                }
                if(!(startSet && endSet)) throw new IllegalArgumentException("Argument month not valid");
            }
        } else if(isSpecialCharacterPresent(STAR)){
            if(!isValidStar()) {
                throw new IllegalArgumentException("Argument month not valid");
            } else {
                parserParams.setStart(START_MONTH);
                parserParams.setEnd(END_MONTH);
            }
        } else if(!isSpecialCharacterPresent(COMMA)) {
            if(parseString.matches("\\d+")) {
                parserParams.setStart(Integer.valueOf(parseString));
                parserParams.setEnd(isSpecialCharacterPresent(SLASH) ? END_MONTH:parserParams.getStart());
            } else {
                Boolean set=false;
                for (MONTH month : MONTH.values()) {
                    if (month.name().equalsIgnoreCase(parseString)) {
                        parserParams.setStart(month.getValue());
                        parserParams.setEnd(isSpecialCharacterPresent(SLASH) ? END_MONTH:parserParams.getStart());
                        set = true;
                        break;
                    }
                }
                if(!set) throw new IllegalArgumentException("Argument month not valid");
            }
        }
        validateRange(start,end,parserParams.getStart(),parserParams.getEnd());
    }

    @Override
    public String toString() {
        return "month         "+super.toString();
    }
}
