import org.apache.log4j.Logger;
import org.junit.Test;

public class Log4j1 {
    //    http://artamonov.ru/2007/04/06/vvedenie-v-log4j/
    private static final Logger LOG = Logger.getLogger(Log4j1.class);

    @Test
    public void testLog() {
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        LOG.fatal("fatal message");
    }
}
