import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtilTests {

    @Test
    public void getTimeZone() {
        System.out.println(Calendar.getInstance().getTimeZone());
        System.out.println(Calendar.getInstance().getTimeZone().getDisplayName());
        System.out.println(Calendar.getInstance().getTimeZone().getDisplayName(Locale.CHINA));
        System.out.println(Calendar.getInstance().getTimeZone().getDisplayName(new Locale("fr", "CA")));
        System.out.println(Calendar.getInstance().getTimeZone().getDisplayName(new Locale("ru")));
        System.out.println(TimeZone.getDefault().getDisplayName());

        System.out.println(TimeZone.getTimeZone(ZoneId.systemDefault()));
        System.out.println(TimeZone.getTimeZone("Europe/Kiev"));
        System.out.println("-------------------");
//        Arrays.stream(TimeZone.getAvailableIDs()).forEach(System.out::println);
        System.out.println("-------------------");
        long millis = 1528116462229l;
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.of("Europe/Kiev")));
    }

    @Test
    public void name() {
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
    }
}
