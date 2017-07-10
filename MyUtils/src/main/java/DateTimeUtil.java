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
}
