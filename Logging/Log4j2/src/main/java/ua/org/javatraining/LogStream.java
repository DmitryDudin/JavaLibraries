package ua.org.javatraining;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.stream.Stream;

public class LogStream {
    private static final Logger LOG = LogManager.getLogger(LogStream.class);

    private static int i = 0;

    public static void generateInfinityLog() {
//        final int[] i = {0};
        String key = "key";
        String value = "value_";
        Stream.generate(() -> i++)
                .peek((j) -> timeDelay(500))
                .peek((j) -> LOG.error("\"Generate error LOG - " + j + "\""))
                .peek((j) -> LOG.info("\"Generate info LOG - " + j + "\""))
                .peek((j) -> LOG.debug("\"Generate debug LOG - " + j + "\""))
                .peek((j) -> {
                    if (j % 10 == 0 && j != 0) {
                        ThreadContext.put(key, value + j);
                        ThreadContext.pop();
                        ThreadContext.push("Stack=" + j);
                    }
                })
                .count();
    }

    private static void timeDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
