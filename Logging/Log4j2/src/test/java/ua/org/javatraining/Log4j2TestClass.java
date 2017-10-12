package ua.org.javatraining;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import ua.org.javatraining.branch1.Branch1;
import ua.org.javatraining.branch2.Branch2;
import ua.org.javatraining.branch3.Branch3;

//https://logging.apache.org/log4j/2.x/manual/customconfig.html
public class Log4j2TestClass {
    Logger LOG = LogManager.getLogger(Log4j2TestClass.class);

    @Test
    public void simpleTest() {
//        LOG.info("info");
        LOG.error("error message");//work without file config
    }

    @Test
    public void logInfoTest() {
        LOG.debug("minus one debug message");
        LOG.warn("zero warn message");
        LOG.info("first info message");
        LOG.error("second error message");
        LOG.fatal("second fatal message");
    }

    @Test
    public void branch1Tests() {
        Branch1 branch1 = new Branch1();
        branch1.logInfoBranch1();
        branch1.logDebugBranch1();
        branch1.logErrorBranch1();
    }

    @Test
    public void branch2Tests() {
        Branch2 branch2 = new Branch2();
        branch2.logInfoBranch2();
        branch2.logDebugBranch2();
        branch2.logErrorBranch2();
    }

    @Test
    public void branch3Tests() {
        Branch3 branch3 = new Branch3();
        branch3.logInfoBranch3();
        branch3.logDebugBranch3();
        branch3.logErrorBranch3();
    }
}
