package ua.org.javatraining.branch3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class Branch3 {
    private final Logger LOG = LogManager.getLogger(Branch3.class);
    private int limit = 3;

    public void logInfoBranch3() {
        for (int i = 0; i < limit; i++) {
            LOG.info("\"branch3 message - " + i + "\"");
        }
    }

    public void logDebugBranch3() {
        int seed = 0;
        Stream.iterate(seed, (n) -> (n + 1))
                .limit(limit)
                .forEach(i -> LOG.debug("\"branch3 message - " + i + "\""));
    }

    public void logErrorBranch3() {
        for (int i = 0; i < limit; i++) {
            LOG.error("\"branch3 message - " + i + "\"");
        }
    }
}
