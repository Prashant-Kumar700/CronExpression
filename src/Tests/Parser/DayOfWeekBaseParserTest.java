package Tests.Parser;

import com.example.cron.Parser.BaseParser;
import com.example.cron.Parser.DayOfMonthBaseParser;
import com.example.cron.Parser.DayOfWeekBaseParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class DayOfWeekBaseParserTest {

    @Test
    void testSingleValue() {
        BaseParser dayOfWeekBaseParser = new DayOfWeekBaseParser("3");
        List<Integer> occurances = new ArrayList<>(){
            {
                add(3);
            }
        };

        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences().size(),1);
        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences(),occurances);
    }

    @Test
    void testSingleValueAlphabets() {
        BaseParser dayOfWeekBaseParser = new DayOfWeekBaseParser("MON");
        List<Integer> occurances = new ArrayList<>(){
            {
                add(1);
            }
        };

        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences().size(),1);
        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences(),occurances);
    }

    @Test
    void testInvalidSingleValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DayOfWeekBaseParser("90"));
    }

    @Test
    void testInvalidValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DayOfWeekBaseParser("test"));
    }

    @Test
    void testRange() {
        BaseParser dayOfWeekBaseParser = new DayOfWeekBaseParser("1-5");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
            }
        };

        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences().size(),5);
        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testRangeAlphabet() {
        BaseParser dayOfWeekBaseParser = new DayOfWeekBaseParser("MON-WED");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(1);
                add(2);
                add(3);
            }
        };

        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences().size(),3);
        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testInvalidRange() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DayOfWeekBaseParser("5-1"));
    }

    @Test
    void testInvalidRangeAlphabet() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DayOfWeekBaseParser("FRI-TUE"));
    }

    @Test
    void testStepWithValidInitialValue() {
        BaseParser dayOfWeekBaseParser = new DayOfWeekBaseParser("3/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(3);
                add(5);
            }
        };
        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithValidInitialValueAlphabet() {
        BaseParser dayOfWeekBaseParser = new DayOfWeekBaseParser("WED/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(3);
                add(5);
            }
        };
        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithStar() {
        BaseParser dayOfWeekBaseParser = new DayOfWeekBaseParser("*/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(0);
                add(2);
                add(4);
                add(6);
            }
        };
        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithRange() {
        BaseParser dayOfWeekBaseParser = new DayOfWeekBaseParser("2-6/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(2);
                add(4);
                add(6);
            }
        };
        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithRangeAlphabet() {
        BaseParser dayOfWeekBaseParser = new DayOfWeekBaseParser("TUE-SAT/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(2);
                add(4);
                add(6);
            }
        };
        Assertions.assertEquals(dayOfWeekBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithNonRange() {
        Assertions.assertThrows(AssertionError.class, () -> new DayOfWeekBaseParser("1,5/10"));
    }

    @Test
    void testStepWithNonRangeAlphabet() {
        Assertions.assertThrows(AssertionError.class, () -> new DayOfWeekBaseParser("MON,TUE/10"));
    }
}