package ua.com.javatraining.randomStringUtils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

public class TestRandomStringUtils {

    @Test
    public void generateRandomSuffixForTests() {
        System.out.println(TestRandomStringUtils.randomSuffix("ABC_"));
    }

    private static String randomSuffix(String value) {
        return value + RandomStringUtils.randomNumeric(10);
    }

}
