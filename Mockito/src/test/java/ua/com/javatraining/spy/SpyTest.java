package ua.com.javatraining.spy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class SpyTest {

    @Spy
//    List<String> list = new ArrayList<>();
            List<String> list;

    @Test
    public void shouldReturnValue() {
//        when(list.get(0)).thenReturn("value");//IndexOutOfBoundsException
        doReturn("value").when(list).get(0);
        System.out.println(list.get(0));
    }

    @Test
    public void shouldReturnNull() {
        list.add("some string");
        System.out.println(list);
        System.out.println(list.get(0));//null if list not initialized with new
        System.out.println("size= " + list.size());
    }

    @Test
    public void shouldReturnNotNull() {
        List list2 = Mockito.spy(new ArrayList<>());

        list2.add("some string 1");
        System.out.println(list2.get(0));

        doReturn("some string 2").when(list2).get(anyInt());
        System.out.println(list2.get(0));

        when(list.get(0)).thenReturn("some string 3");
        System.out.println(list.get(0));

        System.out.println("after= " + list2.get(0));
        System.out.println("after= " + list2.get(0));
        System.out.println("after= " + list2.get(0));
    }

    @Test
    public void shouldReturn() {
        doReturn("value2").when(list).get(anyInt());
        System.out.println(list.get(0));
    }

}
