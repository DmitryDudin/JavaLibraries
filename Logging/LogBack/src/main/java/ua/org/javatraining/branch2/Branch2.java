package ua.org.javatraining.branch2;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class Branch2 {

    private final Logger LOG = LoggerFactory.getLogger(Branch2.class);

    private int limit = 3;

    public void logInfoBranch2() {
        IntStream.range(0, limit)
                .forEach(i -> LOG.info("\"branch3 message - " + i + "\""));
    }

    public void logDebugBranch2() {
        IntStream.range(0, limit)
                .forEach(i -> LOG.debug("\"branch3 message - " + i + "\""));
    }

    public void logErrorBranch2() {
        IntStream.range(0, limit)
                .forEach(i -> LOG.error("\"branch3 message - " + i + "\""));
    }

    public void getException() throws Exception {
        try {
            throw new Exception("Branch2 exception.");
        } catch (Exception e) {
            LOG.error("!!! Exception !!!", e);
        }
    }

    public void getRuntimeException() {
        try {
            throw new RuntimeException("Branch2 runtimeException.");
        } catch (RuntimeException e) {
            LOG.error("!!! RuntimeException !!!", e);
        }
    }

    public void getThrowable() throws Throwable {
        try {
            throw new Throwable("Branch2 Throwable.");
        } catch (Throwable e) {
            LOG.error("!!! Throwable !!!", e);
        }
    }

}
