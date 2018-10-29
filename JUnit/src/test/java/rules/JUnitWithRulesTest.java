package rules;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import ua.com.javatraining.MathUtils;

import static org.hamcrest.Matchers.equalTo;

public class JUnitWithRulesTest {
//    https://github.com/junit-team/junit4/wiki/rules
//    Rules allow very flexible addition or redefinition of the behavior of each test method in a test class.
//    Testers can reuse or extend one of the provided Rules , or write their own.

//    Base Rules:
//    1. TemporaryFolder  - allows creation of files and folders that are deleted when the test method
//       finishes (whether it passes or fails).
//    2. ExternalResource - set up an external resource before a test (a file, socket, server,
//       database connection, etc.), and guarantee to tear it down afterward.
//    3. ErrorCollector - allows execution of a test to continue after the first problem is found (for example,
//       to collect all the incorrect rows in a table, and report them all at once).
//    4. Verifier
//    5. TestWatchman/TestWatcher
//    6. TestName - makes the current test name available inside test methods
//    7. Timeout - applies the same timeout to all test methods in a class
//    8. ExpectedException - allows in-test specification of expected exception types and messages
//    9.
//    10.

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testWithErrorCollector() {
        errorCollector.checkThat("1/1=1", MathUtils.divide(1d, 1d), equalTo(2));
        errorCollector.checkThat("2/1 should be 2", MathUtils.divide(2d, 1d), equalTo(3));
    }

    @Test
    public void testWithExpectedException() {
//        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage(Matchers.is((String) null));
//        try {

        MathUtils.divide(null, null);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

}
