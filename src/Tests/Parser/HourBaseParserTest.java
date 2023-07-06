package Tests.Parser;

import com.example.cron.Parser.BaseParser;
import com.example.cron.Parser.DayOfWeekBaseParser;
import com.example.cron.Parser.HourBaseParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class HourBaseParserTest {

    @Test
     void testSingleValue() {
        BaseParser hourBaseParser = new HourBaseParser("3");
        List<Integer> occurances = new ArrayList<>(){
            {
                add(3);
            }
        };

        Assertions.assertEquals(hourBaseParser.getParserParams().getOccurrences().size(),1);
        Assertions.assertEquals(hourBaseParser.getParserParams().getOccurrences(),occurances);
    }

    @Test
    void testInvalidSingleValue() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new HourBaseParser("90"));
    }

    @Test
    void testInvalidValue() {
        Assertions.assertThrows(NumberFormatException.class, () -> new HourBaseParser("test"));
    }

    @Test
    void testRange() {
        BaseParser hourBaseParser = new HourBaseParser("1-5");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(1);
                add(2);
                add(3);
                add(4);
                add(5);
            }
        };

        Assertions.assertEquals(hourBaseParser.getParserParams().getOccurrences().size(),5);
        Assertions.assertEquals(hourBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testInvalidRange() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new HourBaseParser("5-1"));
    }

    @Test
    void testStepWithValidInitialValue() {
        BaseParser hourBaseParser = new HourBaseParser("3/10");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(3);
                add(13);
                add(23);
            }
        };
        Assertions.assertEquals(hourBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithStar() {
        BaseParser hourBaseParser = new HourBaseParser("*/10");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(0);
                add(10);
                add(20);
            }
        };
        Assertions.assertEquals(hourBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithRange() {
        BaseParser hourBaseParser = new HourBaseParser("2-20/10");
        List<Integer> occurrences = new ArrayList<>(){
            {
                add(2);
                add(12);
            }
        };
        Assertions.assertEquals(hourBaseParser.getParserParams().getOccurrences(),occurrences);
    }

    @Test
    void testStepWithNonRange() {
        Assertions.assertThrows(AssertionError.class, () -> new HourBaseParser("1,5/10"));
    }
}