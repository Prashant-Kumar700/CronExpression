package Tests.Parser;

import com.example.cron.Parser.BaseParser;
import com.example.cron.Parser.DayOfWeekBaseParser;
import com.example.cron.Parser.MonthBaseParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MonthBaseParserTest {

    @Test
    void testSingleValue() {
        BaseParser monthBaseParser = new MonthBaseParser("3");
        List<Integer> occurances = new ArrayList<>(){
            {
                add(3);
            }
        };

        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences().size(),1);
        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences(),occurances);
    }

    @Test
    void testSingleValueAlphabets() {
        BaseParser monthBaseParser = new MonthBaseParser("JAN");
        List<Integer> occurances = new ArrayList<>(){
            {
                add(1);
            }
        };

        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences().size(),1);
        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences(),occurances);
    }

    @Test
    void testInvalidSingleValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MonthBaseParser("90"));
    }

    @Test
    void testInvalidValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MonthBaseParser("test"));
    }

    @Test
    void testRange() {
        BaseParser monthBaseParser = new MonthBaseParser("1-5");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
            }
        };

        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences().size(),5);
        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testRangeAlphabet() {
        BaseParser monthBaseParser = new MonthBaseParser("JAN-MAR");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(1);
                add(2);
                add(3);
            }
        };

        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences().size(),3);
        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testInvalidRange() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MonthBaseParser("5-1"));
    }

    @Test
    void testInvalidRangeAlphabet() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MonthBaseParser("MAR-JAN"));
    }

    @Test
    void testStepWithValidInitialValue() {
        BaseParser monthBaseParser = new MonthBaseParser("3/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(3);
                add(5);
                add(7);
                add(9);
                add(11);
            }
        };
        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithValidInitialValueAlphabet() {
        BaseParser monthBaseParser = new MonthBaseParser("MAR/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(3);
                add(5);
                add(7);
                add(9);
                add(11);
            }
        };
        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithStar() {
        BaseParser monthBaseParser = new MonthBaseParser("*/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(1);
                add(3);
                add(5);
                add(7);
                add(9);
                add(11);
            }
        };
        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithRange() {
        BaseParser monthBaseParser = new MonthBaseParser("2-6/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(2);
                add(4);
                add(6);
            }
        };
        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithRangeAlphabet() {
        BaseParser monthBaseParser = new MonthBaseParser("FEB-JUN/2");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(2);
                add(4);
                add(6);
            }
        };
        Assertions.assertEquals(monthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithNonRange() {
        Assertions.assertThrows(AssertionError.class, () -> new DayOfWeekBaseParser("1,5/10"));
    }

    @Test
    void testStepWithNonRangeAlphabet() {
        Assertions.assertThrows(AssertionError.class, () -> new DayOfWeekBaseParser("JAN,FEB/10"));
    }
}