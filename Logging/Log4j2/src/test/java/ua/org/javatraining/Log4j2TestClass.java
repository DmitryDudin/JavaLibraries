package ua.org.javatraining;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import ua.org.javatraining.branch1.Branch1;
import ua.org.javatraining.branch2.Branch2;
import ua.org.javatraining.branch3.Branch3;
import ua.org.javatraining.branch3.branch3_1.Branch3_1;

//https://logging.apache.org/log4j/2.x/manual/customconfig.html
public class Log4j2TestClass {
    private static final Logger LOG = LogManager.getLogger(Log4j2TestClass.class);

    @Ignore
    @Test
    public void simpleTest() {
//        LOG.info("info");
        LOG.error("0 error message");//work without file config
    }

    @Test
    public void logInfoTest() {
        LOG.debug("1 debug message");
        LOG.warn("2 info message");
        LOG.info("3 warn message");
        LOG.error("4 error message");
        LOG.fatal("5 fatal message");
    }

    @Test
    public void branch1Tests() {
        Branch1 branch1 = new Branch1();
        branch1.logDebugBranch1();
        branch1.logInfoBranch1();
        branch1.logErrorBranch1();
    }

    @Test
    public void branch2Tests() throws Throwable {
        Branch2 branch2 = new Branch2();
        branch2.logDebugBranch2();
        branch2.logInfoBranch2();
        branch2.logErrorBranch2();
        branch2.getException();
//        branch2.getRuntimeException();
//        branch2.getThrowable();
    }

    @Test
    public void branch3Tests() {
        Branch3 branch3 = new Branch3();
        branch3.logDebugBranch3();
        branch3.logInfoBranch3();
        branch3.logErrorBranch3();
    }

    @Test
    public void branch3_1Tests() {
        Branch3_1 branch3_1 = new Branch3_1();
        branch3_1.logDebugBranch3_1();
        branch3_1.logInfoBranch3_1();
        branch3_1.logErrorBranch3_1();
    }

    @Ignore
    @Test
    public void generateTest() {
        LogStream.generateInfinityLog();
    }
}
