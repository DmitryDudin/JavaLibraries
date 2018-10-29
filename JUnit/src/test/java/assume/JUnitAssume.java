package assume;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ua.com.javatraining.MathUtils;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class JUnitAssume {

//    https://github.com/junit-team/junit4/wiki/Assumptions-with-assume
//    The default JUnit runner treats tests with failing assumptions as ignored.

    enum TestType {
        POSITIVE, NEGATIVE
    }

    private Double result;
    private Double x;
    private Double y;
    private TestType testType;

    @Parameterized.Parameters(name = "{index}: div({1}/{2})")
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {TestType.POSITIVE, 1.0, 1.0, 1.0},
                {TestType.POSITIVE, 10.0, 1.0, 10.0},
                {TestType.POSITIVE, 1.0, 10.0, 0.1},

                {TestType.NEGATIVE, null, 10.0, null},
                {TestType.NEGATIVE, 1.0, null, null},
                {TestType.NEGATIVE, null, null, null},
        });
    }

    public JUnitAssume(TestType testType, Double x, Double y, Double result) {
        this.testType = testType;
        this.x = x;
        this.y = y;
        this.result = result;
    }

    @Test
    public void shouldDivideValues_positive() {
        Assume.assumeTrue(testType == TestType.POSITIVE);
        assertThat(MathUtils.divide(x, y), equalTo(result));
    }

    @Test(expected = Exception.class)
    public void shouldThrowException_negative() {
        Assume.assumeTrue(testType == TestType.NEGATIVE);
        MathUtils.divide(x, y);
    }

}
