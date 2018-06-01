package ua.com.javatraining;

//import org.junit.Assert;
//import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@Slf4j
public class SimpleTest {
    private static Logger LOG = LoggerFactory.getLogger(SimpleTest.class);

    @Test
    @DisplayName("First test")
    public void customtest3() {
//        System.setOut(StringS);
        LOG.info("logger Test 1");

        System.out.println("Test 1");

//        Assert.assertEquals(true, new Boolean(true).booleanValue());
        Assertions.assertEquals(true, new Boolean(true).booleanValue());
        assertAll(
                () -> assertEquals(12, new Integer(12).intValue())
        );

    }

//    @Disabled
    @Test
    @DisplayName("Secong test")
    public void customtest2() {
        System.out.println("Test 2");
//        Assert.assertEquals(true, new Boolean(true).booleanValue());
//        Assertions.assertEquals(true, new Boolean(true).booleanValue());
        /*assertAll(
                () -> assertEquals(12, new Integer(12).intValue())
        );*/

    }
}
