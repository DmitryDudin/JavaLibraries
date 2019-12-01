package ua.com.javatraining;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockitoSimpleTest {
    @Test
    void test() {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

// stubbing appears before the actual execution
        when(mockedList.get(0)).thenReturn("abra abra");

// the following prints "sourceDest"
        System.out.println("mockito get 0 - " + mockedList.get(0));

// the following prints "null" because get(999) was not stubbed
        System.out.println("mockito get 999 - " + mockedList.get(999));
    }

    @Test
    void test2() {
        // mock creation
        List mockedList = mock(List.class);

// using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

// selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }
}
