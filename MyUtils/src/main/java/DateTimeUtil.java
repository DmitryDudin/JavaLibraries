import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateTimeUtil {

//    https://stackoverflow.com/questions/26142864/how-to-get-utc0-date-in-java-8/38061562#38061562
//    https://www.foreach.be/blog/java-8-date-and-time

    /*public static String getPrettyTime(Long timestamp){
        DateTime time = new DateTime(timestamp);
        return new PrettyTime().format(time.toDate());
    }*/

/*
    public static String getPrettyTimeNow(){
        return getPrettyTime(DateTime.now().getMillis());
    }
*/

    public static Date getDateForTimeZone(String timeZone, Long millis) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(timeZone));
        cal.setTimeInMillis(millis);
        return cal.getTime();
    }

    public static Date getDateForTimeZone(String timeZone, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone(timeZone));
        cal.setTime(date);
        return cal.getTime();
    }

    public static LocalDateTime nowUtc() {
//        LocalDateTime.now(ZoneId.of("UTC"));
//        LocalDateTime.now(ZoneId.of("GMT"));
        return LocalDateTime.now(Clock.systemUTC());
    }

    public static long nowUtcMillis() {
//        Clock.systemUTC().millis();
        return Instant.now().toEpochMilli();
    }

    public static LocalDateTime convertMillisInDateTimeUTC(long millis) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.of("UTC"));
        return localDateTime;
    }

    public static boolean dateIsExpired(Long date) {
        return nowUtcMillis() > date;
    }

    public static long minutesToMillis(Long minutes) {
        return minutes * 60 * 1000;
    }

    public static Date convertMillisUtcToDate(long millisUTC, ZoneId zoneId) {
        LocalDateTime ldt6 = LocalDateTime.ofInstant(Instant.ofEpochMilli(millisUTC), zoneId);
        return Date.from(ldt6.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static long parse(String date) {
        Parser parser = new Parser();
        List<DateGroup> parse = parser.parse(date);
        DateGroup dateGroup = parse.get(0);
        dateGroup.getDates().forEach(System.out::println);
        return dateGroup.getDates().get(0).getTime();
    }

    public static Long toMillisUTC(String date, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        return localDateTime.toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
    }

    public static String convertMillisToString(long millisUTC, ZoneId millisZoneId, DateTimeFormatter dateTimeFormatter) {
        return ZonedDateTime
                .ofInstant(Instant.ofEpochMilli(millisUTC), millisZoneId)
                .format(dateTimeFormatter);
    }

    public static Date parse(String str, String... parsePatterns) throws ParseException {
        return DateUtils.parseDate(str, parsePatterns);
    }

    private List<String> dateTimePatterns = new ArrayList<String>() {{
        add("[yyyyMMdd][yyyy-MM-dd][yyyy-DDD]['T'[HHmmss][HHmm][HH:mm:ss][HH:mm][.SSSSSSSSS][.SSSSSS][.SSS][.SS][.S]][OOOO][O][z][XXXXX][XXXX]['['VV']']");
        add("yyyy-MM-dd'T'HH:mm:ss[XXX][X]"); //2018-06-21T08:55:49+03:00
        add("\"yyyy-MM-dd'T'HH:mm:ssXXX"); // 2018-06-21T08:55:49+03:00"
        add("\"yyyy-MM-dd'T'HH:mm:sszzz"); // 2018-06-21T08:55:49+03:00"

    }};
}
