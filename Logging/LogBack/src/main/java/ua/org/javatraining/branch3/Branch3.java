package ua.org.javatraining.branch3;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class Branch3 {

    private final Logger LOG = LoggerFactory.getLogger(Branch3.class);

    private int limit = 3;

    public void logDebugBranch3() {
        IntStream.range(0, limit)
                .forEach(i -> LOG.debug("\"branch3 message - " + i + "\""));
    }

    public void logInfoBranch3() {
        IntStream.range(0, limit)
                .forEach(i -> LOG.info("\"branch3 message - " + i + "\""));
    }

    public void logErrorBranch3() {
        IntStream.range(0, limit)
                .forEach(i -> LOG.error("\"branch3 message - " + i + "\""));
    }
}
