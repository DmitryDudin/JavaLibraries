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
public class JUnitParameterizedTest {

    private Double result;
    private Double x;
    private Double y;

    //    Annotation for a method which provides parameters to be injected into the test class
    // constructor by Parameterized.
    //The method has to be public and static.
    @Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {0.0, 0.0, 0.0},
                {1.0, 1.0, 1.0},
                {10.0, 1.0, 10.0},
                {1.0, 10.0, 0.1},
        });
    }

    public JUnitParameterizedTest(Double x, Double y, Double result) {
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
