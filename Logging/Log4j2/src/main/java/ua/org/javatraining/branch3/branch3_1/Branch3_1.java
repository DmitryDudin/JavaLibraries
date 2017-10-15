package ua.org.javatraining.branch3.branch3_1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Stream;

public class Branch3_1 {
    private final Logger LOG = LogManager.getLogger(Branch3_1.class);
    private int limit = 3;
    private String brachName = this.getClass().getSimpleName();

    public void logInfoBranch3_1() {
        for (int i = 0; i < limit; i++) {
            LOG.info("\"" + brachName + " message - " + i + "\"");
        }
    }

    public void logDebugBranch3_1() {
        int seed = 0;
        Stream.iterate(seed, (n) -> (n + 1))
                .limit(limit)
//                .forEach(i -> LOG.debug("\"" + brachName + " message - " + i + "\""));
                .forEach(i -> LOG.debug("\"{} message - {}\"", brachName, i));
    }

    public void logErrorBranch3_1() {
        for (int i = 0; i < limit; i++) {
            LOG.error("\"" + brachName + " message - " + i + "\"");
        }
    }
}
