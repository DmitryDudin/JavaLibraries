package ua.org.javatraining.branch1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Branch1 {
    private final Logger LOG = LogManager.getLogger(Branch1.class);

    public void logTraceBranch1() {
        LOG.traceEntry();
        for (int i = 0; i < 3; i++) {
            LOG.trace("\"branch1 message - " + i + "\"");
        }
        LOG.traceExit();
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
