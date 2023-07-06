package Tests.Parser;

import com.example.cron.Parser.BaseParser;
import com.example.cron.Parser.DayOfMonthBaseParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class DayOfMonthBaseParserTest {

    @Test
    void testSingleValue() {
        BaseParser dayOfMonthBaseParser = new DayOfMonthBaseParser("3");
        List<Integer> occurances = new ArrayList<>(){
            {
                add(3);
            }
        };

        Assertions.assertEquals(dayOfMonthBaseParser.getParserParams().getOccurrences().size(),1);
        Assertions.assertEquals(dayOfMonthBaseParser.getParserParams().getOccurrences(),occurances);
    }

    @Test
    void testInvalidSingleValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DayOfMonthBaseParser("90"));
    }

    @Test
    void testInvalidValue() {
        Assertions.assertThrows(NumberFormatException.class, () -> new DayOfMonthBaseParser("test"));
    }

    @Test
    void testRange() {
        BaseParser dayOfMonthBaseParser = new DayOfMonthBaseParser("1-5");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
            }
        };

        Assertions.assertEquals(dayOfMonthBaseParser.getParserParams().getOccurrences().size(),5);
        Assertions.assertEquals(dayOfMonthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testInvalidRange() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new DayOfMonthBaseParser("5-1"));
    }

    @Test
    void testStepWithValidInitialValue() {
        BaseParser dayOfMonthBaseParser = new DayOfMonthBaseParser("3/10");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(3);
                add(13);
                add(23);
            }
        };
        Assertions.assertEquals(dayOfMonthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithStar() {
        BaseParser dayOfMonthBaseParser = new DayOfMonthBaseParser("*/10");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(0);
                add(10);
                add(20);
                add(30);
            }
        };
        Assertions.assertEquals(dayOfMonthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithRange() {
        BaseParser dayOfMonthBaseParser = new DayOfMonthBaseParser("2-20/10");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(2);
                add(12);
            }
        };
        Assertions.assertEquals(dayOfMonthBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithNonRange() {
        Assertions.assertThrows(AssertionError.class, () -> new DayOfMonthBaseParser("9,5/10"));
    }
}