package parameterized.junitparams;

import junitparams.JUnitParamsRunner;
import junitparams.NamedParameters;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.com.javatraining.MathUtils;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest {

//    https://github.com/Pragmatists/JUnitParams
//    https://github.com/Pragmatists/JUnitParams/wiki/Quickstart
//    https://www.baeldung.com/junit-params

    //    params are in test method params, not class fields
    @Parameters(value = {
            "1.0, 1.0, 1.0",
            "10.0, 1.0, 10.0",
            "1.0 | 10.0 | 0.1"
    })
    @Test
    @TestCaseName("test №{index} - div({0}/{1})")
    public void test1(Double x, Double y, Double result) {
        assertThat(MathUtils.divide(x, y), equalTo(result));
    }

    @Parameters(
            value = {
                    "99.0, 1.0, 99.0"
            })
    @Test
    @TestCaseName("test №{index} - div({0}/{1})")
    public void test2(Double x, Double y, Double result) {
        assertThat(MathUtils.divide(x, y), equalTo(result));
    }

    @Parameters(method = "testValues")
    @Test
    public void test3(Double x, Double y, Double result) {
        assertThat(MathUtils.divide(x, y), equalTo(result));
    }

    private Object[] testValues() {
        return new Object[][]{
                {101.0, 1.0, 101.0}
        };
    }

    ;

}
