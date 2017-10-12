package ua.org.javatraining.branch2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Branch2 {
    private final Logger LOG = LogManager.getLogger(Branch2.class);

    public void logInfoBranch2() {
        for (int i = 0; i < 10; i++) {
            LOG.info("\"branch2 message - " + i + "\"");
        }
    }

    public void logDebugBranch2() {
        for (int i = 0; i < 10; i++) {
            LOG.debug("\"branch2 message - " + i + "\"");
        }
    }

    public void logErrorBranch2() {
        for (int i = 0; i < 10; i++) {
            LOG.error("\"branch2 message - " + i + "\"");
        }
    }
}
