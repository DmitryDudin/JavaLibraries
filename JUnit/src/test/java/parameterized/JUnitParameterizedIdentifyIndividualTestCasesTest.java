package parameterized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ua.com.javatraining.MathUtils;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class JUnitParameterizedIdentifyIndividualTestCasesTest {

    private Double result;
    private Double x;
    private Double y;

    //set names for tests
    @Parameters(name = "test №{index} - div({0}/{1})")
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {1.0, 1.0, 1.0},
                {10.0, 1.0, 10.0},
                {1.0, 10.0, 0.1},
//                {null, 1.0, 1.0}
        });
    }

    public JUnitParameterizedIdentifyIndividualTestCasesTest(Double x, Double y, Double result) {
        this.result = result;
        this.x = x;
        this.y = y;
    }

    @Test
    public void shouldDivideValues() {
        Double divide = MathUtils.divide(x, y);
        assertThat(divide, equalTo(result));
    }

}
