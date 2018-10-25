package ua.com.javatraining.mock;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoVerify {

    @Test
    public void verifySimpleInvokationOnMock() {
        List<String> mockList = Mockito.mock(MyList.class);
        mockList.size();
        Mockito.verify(mockList).size();
    }


    @Test
    public void verifyNumberOfInteractionsWithMock() {
        List<String> mockList = mock(MyList.class);
        mockList.size();
        mockList.size();
        Mockito.verify(mockList, Mockito.times(2)).size();
    }

    @Test
    public void verifyNoInteractionWithTheWholeMockOccurred() {
        List<String> mockList = mock(MyList.class);
        Mockito.verifyZeroInteractions(mockList);
    }

    @Test
    public void verifyNoInteractionWithSpecificMethodOccurred() {
        List<String> mockList = mock(MyList.class);
        verify(mockList, times(0)).size();
    }

    @Test
    public void verifyThereAreNoUnexpectedInteractions() {
        List<String> mockList = mock(MyList.class);
        mockList.size();
        verify(mockList).size();
        Mockito.verifyNoMoreInteractions(mockList);
    }

    @Test
    public void verifyOrderOfInteractions() {
        List<String> mockList = mock(MyList.class);
        mockList.size();
        mockList.add(Mockito.anyString());
        mockList.clear();

        InOrder inOrder = Mockito.inOrder(mockList);

        inOrder.verify(mockList).size();
        inOrder.verify(mockList).add(Mockito.anyString());
        inOrder.verify(mockList).clear();
    }

    @Test
    public void verifyAnInteractionHasNotOccurred() {
        List<String> mockList = mock(MyList.class);
        mockList.size();
        verify(mockList, Mockito.never()).clear();
    }

    @Test
    public void verifyAnInteractionHasOccurredAtLeastCertainNumberOfTimes() {
        List<String> mockList = mock(MyList.class);
        mockList.size();
        mockList.size();
        mockList.size();
        verify(mockList, Mockito.atLeast(1)).size();
        verify(mockList, Mockito.atMost(10)).size();
    }

    @Test
    public void verifyInteractionWithExactArgument() {
        List<String> mockList = mock(MyList.class);
        mockList.add("exact argument");
        verify(mockList).add("exact argument");
    }

    @Test
    public void verifyInteractionWithFlexibleOreAnyArgument() {
        List<String> mockList = mock(MyList.class);
        mockList.add("any argument");
        verify(mockList).add(anyString());
    }

    @Test
    public void verifyInteractionUsingArgumentCapture() {
        List<String> mockedList = mock(MyList.class);
//        mockedList.addAll(Lists.<String> newArrayList("someElement"));
        mockedList.addAll(Arrays.asList("someElement"));
        ArgumentCaptor<List> argumentCaptor = ArgumentCaptor.forClass(List.class);
        verify(mockedList).addAll(argumentCaptor.capture());
        List<String> capturedArgument = argumentCaptor.<List<String>>getValue();
//        assertThat(capturedArgument, hasItem("someElement"));
        Assertions.assertThat(capturedArgument).contains("someElement");
    }

    public class MyList extends AbstractList<String> {

        @Override
        public String get(final int index) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    }
}
