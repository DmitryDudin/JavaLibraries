package ua.org.javatraining;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class LogStream {
    private static final Logger LOG = LogManager.getLogger(LogStream.class);

    private static int i = 0;

    public static void generateInfinityLog() {
//        final int[] i = {0};
        Stream.generate(() -> i++)
                .peek((j) -> timeDelay(500))
                .peek((j) -> LOG.error("\"Generate error LOG - " + j + "\""))
                .peek((j) -> LOG.info("\"Generate info LOG - " + j + "\""))
                .forEach((j) -> LOG.debug("\"Generate debug LOG - " + j + "\""));
    }

    private static void timeDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
