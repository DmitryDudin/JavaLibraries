package parameterized;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class JUnitParameterizedWithSingleParameterTest {

    private String parameter;

    @Parameters
    public static Iterable<? extends Object> params() {
        return Arrays.asList("sourceDest", "second", "third");
    }
    //pr
    /*@Parameters
    public static Object[] data() {
        return new Object[] { "sourceDest test", "second test" };
    }*/

    public JUnitParameterizedWithSingleParameterTest(String parameter) {
        this.parameter = parameter;
    }

    @Test
    public void shouldPrintParam() {
        System.out.println(parameter);
    }

}
