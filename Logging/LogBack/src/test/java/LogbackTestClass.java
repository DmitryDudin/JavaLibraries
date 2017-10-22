import ua.org.javatraining.LogStream;
import ua.org.javatraining.branch1.Branch1;
import ua.org.javatraining.branch2.Branch2;
import ua.org.javatraining.branch3.Branch3;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackTestClass {

    private static final Logger LOG = LoggerFactory.getLogger(LogbackTestClass.class);


    @Test
    public void branch1Tests() {
        Branch1 branch1 = new Branch1();
        branch1.logTraceBranch1();
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

    @Ignore
    @Test
    public void generateTest() {
        LogStream.generateInfinityLog();
    }
}
