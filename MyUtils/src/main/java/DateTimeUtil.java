import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtil {


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
        return Clock.systemUTC().millis();
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

}
