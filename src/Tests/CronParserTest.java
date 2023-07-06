package Tests;

import com.example.cron.CronParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CronParserTest {

    @Test
    void testValidParams() {
        CronParser cronParser = new CronParser("*/15 0 1,15 * 1-5 /usr/bin/find");
        String parsedExpression = "minute        0 15 30 45 \n" +
                "hour          0 \n" +
                "day of month  1 15 \n" +
                "month         1 2 3 4 5 6 7 8 9 10 11 12 \n" +
                "day of week   1 2 3 4 5 \n" +
                "command       /usr/bin/find";
        Assertions.assertEquals(cronParser.toString(),parsedExpression);
    }

    @Test
    void testInvalidNumberOfParams() {
        Assertions.assertThrows(AssertionError.class,() -> {
            CronParser cronParser = new CronParser("*/15 0 1,15 * 1-5");
        });
    }
    @Test
    void testWrongParams() {
        Assertions.assertThrows(NumberFormatException.class,() -> {
            CronParser cronParser = new CronParser("some params a a a a");
        });
    }
}