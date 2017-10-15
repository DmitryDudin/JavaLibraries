package ua.org.javatraining.branch2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Branch2 {
    private final Logger LOG = LogManager.getLogger(Branch2.class);

    public void logInfoBranch2() {
        for (int i = 0; i < 3; i++) {
            LOG.info("\"branch2 message - " + i + "\"");
        }
    }

    public void logDebugBranch2() {
        for (int i = 0; i < 3; i++) {
            LOG.debug("\"branch2 message - " + i + "\"");
        }
    }

    public void logErrorBranch2() {
        for (int i = 0; i < 3; i++) {
            LOG.error("\"branch2 message - " + i + "\"");
        }
    }

    public void getException() throws Exception {
        try {
            throw new Exception("Branch2 exception.");
        } catch (Exception e) {
            LOG.error("!!! Exception !!!",e);
        }
    }

    public void getRuntimeException() {
        try {
            throw new RuntimeException("Branch2 runtimeException.");
        } catch (RuntimeException e) {
            LOG.error("!!! RuntimeException !!!",e);
        }
    }

    public void getThrowable() throws Throwable {
        try {
            throw new Throwable("Branch2 Throwable.");
        } catch (Throwable e) {
            LOG.error("!!! Throwable !!!",e);
        }
    }

}
