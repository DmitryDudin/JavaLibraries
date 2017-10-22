package ua.org.javatraining.branch1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class Branch1 {

    private final Logger LOG = LoggerFactory.getLogger(Branch1.class);

    private int limit = 3;

    public void logTraceBranch1() {
//        LOG.traceEntry();
        IntStream.range(0, limit)
                .forEach(i -> LOG.trace("\"branch3 message - " + i + "\""));

//        LOG.traceExit();
    }

    public void logDebugBranch1() {
        for (int i = 0; i < 3; i++) {
            LOG.debug("\"branch1 message - " + i + "\"");
        }
    }

    public void logInfoBranch1() {
        for (int i = 0; i < 3; i++) {
            LOG.info("\"branch1 message - " + i + "\"");
        }
    }

    public void logErrorBranch1() {
        for (int i = 0; i < 3; i++) {
            LOG.error("\"branch1 message - " + i + "\"");
        }
    }
}
