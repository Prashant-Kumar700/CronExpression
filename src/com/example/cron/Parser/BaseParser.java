package com.example.cron.Parser;

import com.example.cron.Model.ParserParams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.cron.Constants.Constants.*;

/*
* Base parser implementation from all the individual parsers.
 */
public class BaseParser {
    String parseString;
    private final Map<String,Boolean> hasSpecialCharacters = new HashMap<>();

    public ParserParams getParserParams() {
        return parserParams;
    }

    ParserParams parserParams = new ParserParams();

    BaseParser(String parseString) {
        this.parseString = parseString;
        SPECIAL_CHARACTERS.forEach((specialCharacter) ->
                hasSpecialCharacters.put(specialCharacter,parseString.contains(specialCharacter)));
    }

    /*
    *
    * If the params contains a step "/" then set the step for the occurrences.
    * Check that the step function is applied to range and not any individual values.
    * Throws AssersionError if the step function is applied to individual values.
     */
    void setStep() {
        if(isSpecialCharacterPresent(COMMA)) {
            throw new AssertionError("Invalid format step can only be applied to ranges.");
        }
        String[] stepSplit = parseString.split(SLASH);
        parserParams.setStep(Integer.valueOf(stepSplit[1]));
        parseString = stepSplit[0];
    }

    /*
    *
    * Set the range of the occurrences for the params.
    * Throws NumberFromatException if the params are not numbers.
    * Throws IllegalArgumentException if the star param is not correctly set.
     */
    void setRange(int start, int end) {
        if(isSpecialCharacterPresent(DASH)) {
            String[] stepRange = parseString.split(DASH);
            parserParams.setStart(Integer.valueOf(stepRange[0]));
            parserParams.setEnd(Integer.valueOf(stepRange[1]));
        } else if(isSpecialCharacterPresent(STAR)){
            if(!isValidStar()) {
                throw new IllegalArgumentException("Star format not correct");
            } else {
                parserParams.setStart(start);
                parserParams.setEnd(end);
            }
        } else if(!isSpecialCharacterPresent(COMMA)) {
            parserParams.setStart(Integer.valueOf(parseString));
            parserParams.setEnd(isSpecialCharacterPresent(SLASH) ? end:parserParams.getStart());
        }
        validateRange(start,end,parserParams.getStart(),parserParams.getEnd());
    }

    /*
    *
    * Validate if the range is a valid range and throws IllegalArgumentException otherwise.
     */
    void validateRange(int start, int end, int start1, int end1) {
        if(start1<start || start1>end || end1<start || end1>end || end1<start1)
            throw new IllegalArgumentException("Not a valid Range");
    }

    /*
    *
    * Set the occurrence for each params passed and store it in a list.
     */
    void setOccurrences() {
        List<Integer> temp = new ArrayList<>();
        if(!isSpecialCharacterPresent(COMMA)){
            for(int min = parserParams.getStart(); min<= parserParams.getEnd();min += parserParams.getStep()) {
                temp.add(min);
            }
        } else {
            for(String min : parseString.split(COMMA)) {
                temp.add(Integer.valueOf(min));
            }
        }
        parserParams.setOccurrences(temp);
    }

    @Override
    public String toString() {
        String str = "";
        for(int val: parserParams.getOccurrences()) {
            str += val+" ";
        }
        return str;
    }

    /*
    *
    * Check if a special character is present in the param.
     */
    boolean isSpecialCharacterPresent(String specialCharacter) {
        return hasSpecialCharacters.get(specialCharacter);
    }

    /*
    * Check if the star format is valid.
     */
    boolean isValidStar() {
        return STAR.compareTo(parseString)==0;
    }
}
