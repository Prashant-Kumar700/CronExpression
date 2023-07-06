package Tests.Parser;

import com.example.cron.Parser.BaseParser;
import com.example.cron.Parser.DayOfWeekBaseParser;
import com.example.cron.Parser.MinuteBaseParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MinuteBaseParserTest {
    @Test
    void testSingleValue() {
        BaseParser minuteBaseParser = new MinuteBaseParser("3");
        List<Integer> occurances = new ArrayList<>(){
            {
                add(3);
            }
        };

        Assertions.assertEquals(minuteBaseParser.getParserParams().getOccurrences().size(),1);
        Assertions.assertEquals(minuteBaseParser.getParserParams().getOccurrences(),occurances);
    }

    @Test
    void testInvalidSingleValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MinuteBaseParser("90"));
    }

    @Test
    void testInvalidValue() {
        Assertions.assertThrows(NumberFormatException.class, () -> new MinuteBaseParser("test"));
    }

    @Test
    void testRange() {
        BaseParser minuteBaseParser = new MinuteBaseParser("1-5");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
            }
        };

        Assertions.assertEquals(minuteBaseParser.getParserParams().getOccurrences().size(),5);
        Assertions.assertEquals(minuteBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testInvalidRange() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MinuteBaseParser("5-1"));
    }

    @Test
    void testStepWithValidInitialValue() {
        BaseParser minuteBaseParser = new MinuteBaseParser("3/10");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(3);
                add(13);
                add(23);
                add(33);
                add(43);
                add(53);
            }
        };
        Assertions.assertEquals(minuteBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithStar() {
        BaseParser minuteBaseParser = new MinuteBaseParser("*/10");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(0);
                add(10);
                add(20);
                add(30);
                add(40);
                add(50);
            }
        };
        Assertions.assertEquals(minuteBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithRange() {
        BaseParser minuteBaseParser = new MinuteBaseParser("2-20/10");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(2);
                add(12);
            }
        };
        Assertions.assertEquals(minuteBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithNonRange() {
        Assertions.assertThrows(AssertionError.class, () -> new MinuteBaseParser("1,5/10"));
    }
}