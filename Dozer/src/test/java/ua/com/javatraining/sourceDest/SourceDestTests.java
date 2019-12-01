package ua.com.javatraining.sourceDest;

import org.dozer.DozerBeanMapper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SourceDestTests {

    final DozerBeanMapper mapper = new DozerBeanMapper();

    @Test
    public void mapObjectToClass() {
        Source source = new Source("testName", 3);
        Dest dest = mapper.map(source, Dest.class);

        assertThat(dest.getAge()).isEqualTo(source.getAge());
        assertThat(dest.getName()).isEqualTo(source.getName());

        System.out.println("dest= " + dest);
    }

    @Test
    public void simpleBetweenObjects() {
        Source source = new Source("testName", 3);
        Dest dest = new Dest();
        mapper.map(source, dest);

        assertThat(dest.getAge()).isEqualTo(source.getAge());
        assertThat(dest.getName()).isEqualTo(source.getName());

        System.out.println("dest= " + dest);
    }

}
