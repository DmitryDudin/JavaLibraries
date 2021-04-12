package ua.com.javatraining;

import org.junit.Test;

import java.util.Random;

public class CustomAssertError {

    @Test
    public void throwCustomAssertError() {

        if (new Random().nextBoolean()) {
            throw new AssertionError("__custom___message__", new RuntimeException());
        }
    }

}
