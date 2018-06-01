package ua.com.javatraining;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.AbstractList;

import static org.mockito.Mockito.mock;

public class MockitoMockMethods {

    @Test
    public void simpleMocking() {
        MyList listMock = mock(MyList.class);
        Mockito.when(listMock.add(Mockito.anyString())).thenReturn(false);
        Assertions.assertThat(listMock.add(RandomStringUtils.randomAlphabetic(3))).isFalse();

        Mockito.verify(listMock).add(Mockito.anyString());
        Mockito.verify(listMock).add(Mockito.anyString());
        Mockito.verify(listMock, Mockito.times(1)).add(Mockito.anyString());
    }

    public class MyList extends AbstractList<String> {
        @Override
        public String get(int index) {
            return null;
        }

        @Override
        public int size() {
            return 1;
        }
    }
}
