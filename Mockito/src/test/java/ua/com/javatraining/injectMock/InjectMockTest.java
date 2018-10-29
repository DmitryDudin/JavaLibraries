package ua.com.javatraining.injectMock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.javatraining.Child;
import ua.com.javatraining.Parent;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class InjectMockTest {

    @Mock
    Child child;

    @InjectMocks
    Parent parent;

    @Test
    public void name() {
        when(child.printName()).thenReturn("child printName mock!!!");
        parent.printName();
        Child child = parent.getChild();
        System.out.println(child.printName());
    }
}
