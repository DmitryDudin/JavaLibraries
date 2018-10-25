package ua.com.javatraining.mock;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.AbstractList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MockitoWhenAndThen {

    @Test
    public void configureSimpleReturnBehaviorForMock() {
        List mockList = mock(MyList.class);
        when(mockList.add(anyString())).thenReturn(false);
        assertThat(mockList.add(anyString())).isFalse();
    }

    @Test
    public void configureReturnBehaviorForMockInAnAlternativeWay() {
        List mockList = mock(MyList.class);
        Mockito.doReturn(false).when(mockList).add(anyString());
        assertThat(mockList.add(RandomStringUtils.randomAlphabetic(3))).isFalse();
    }

    @Test(expected = IllegalStateException.class)
    public void configureMockToThrowAnExceptionOnMethodCall() {
        List mockList = mock(MyList.class);
        when(mockList.add(anyString())).thenThrow(IllegalStateException.class);
        mockList.add(randomAlphabetic(3));
    }

    @Test(expected = NullPointerException.class)
    public void configureTheBehaviorOfMethodWithVoidReturnType() {
        List mockList = mock(MyList.class);
        doThrow(NullPointerException.class).when(mockList).clear();
        mockList.clear();
    }

    @Test(expected = IllegalStateException.class)
    public void configureTheBehaviorOfMultipleCalls() {
        List mockList = mock(MyList.class);
        when(mockList.add(anyString()))
                .thenReturn(false)
                .thenThrow(IllegalStateException.class);
        assertThat(mockList.add(randomAlphabetic(3))).isFalse();
        mockList.add(randomAlphabetic(3));// will throw the exception
    }

    @Test(expected = IllegalStateException.class)
    public void configureTheBehaviorOfSpy() {
        MyList myList = new MyList();
        MyList spy = Mockito.spy(myList);
        doThrow(IllegalStateException.class).when(spy).clear();
        spy.clear();
    }

    @Test
    public void configureMethodToCallTheRealUnderlyingMethodOnMock() {
        List mockList = mock(MyList.class);
        when(mockList.size()).thenCallRealMethod();
        assertThat(mockList.size()).isEqualTo(1);
    }

    @Test
    public void configureMockMethodCallWithCustomAnswer() {
        List<String> mockList = mock(MyList.class);
        doAnswer(invocation -> "Always the same.").when(mockList).get(anyInt());
        assertThat(mockList.get(1)).isEqualTo("Always the same.");
    }


    public class MyList extends AbstractList<String> {

        @Override
        public String get(final int index) {
            return null;
        }

        @Override
        public int size() {
            return 1;
        }
    }
}
