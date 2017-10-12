package ua.org.javatraining.branch3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Branch3 {
    private final Logger LOG = LogManager.getLogger(Branch3.class);

    public void logInfoBranch3() {
        for (int i = 0; i < 10; i++) {
            LOG.info("\"branch3 message - " + i + "\"");
        }
    }

    public void logDebugBranch3() {
        for (int i = 0; i < 10; i++) {
            LOG.debug("\"branch3 message - " + i + "\"");
        }
    }

    public void logErrorBranch3() {
        for (int i = 0; i < 10; i++) {
            LOG.error("\"branch3 message - " + i + "\"");
        }
    }
}
