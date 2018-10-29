package ua.com.javatraining.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockWithAnnotation {

    @Mock
    List<String> list;

    @Test
    public void shouldReturnValue() {
        when(list.get(0)).thenReturn("value");
        System.out.println(list.get(0));
    }

    @Test
    public void shouldReturnNull() {
        list.add("some string");
        System.out.println(list.get(0));
    }

    @Test
    public void shouldReturn() {
        doReturn("value2").when(list).get(anyInt());
        System.out.println(list.get(0));
    }

}
