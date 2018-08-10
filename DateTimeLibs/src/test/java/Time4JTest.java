import net.time4j.CalendarUnit;
import net.time4j.Duration;
import net.time4j.PlainDate;
import net.time4j.range.DateInterval;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Time4JTest {
    //    https://github.com/MenoData/Time4J
    //    http://www.time4j.net/
    @Test
    public void test1() {
        LocalDate initial = LocalDate.now();
//        LocalDate initial = LocalDate.now();
//        LocalDate start = initial.withDayOfMonth(1);
        LocalDate start = LocalDate.parse("2016-01-01");
//        LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
        LocalDate end = LocalDate.parse("2017-12-31");
        DateInterval dateInterval = DateInterval.between(start, end);

        List<LocalDate> localDateStream = dateInterval.streamDaily()
                .map(PlainDate::toTemporalAccessor)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

}
