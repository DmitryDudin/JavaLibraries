package ua.com.javatraining.differentAttributesTypes;

import org.dozer.DozerBeanMapper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class DifferentAttributesTypes {

    @Test
    public void mapObjectToClass() {
        DozerBeanMapper mapper = new DozerBeanMapper();

        ClazzWithAttributeString source = new ClazzWithAttributeString("12", 3.1243d, "true");
        ClazzWithAttributeInt dest = mapper.map(source, ClazzWithAttributeInt.class);

        assertThat(dest.getId()).isEqualTo(Integer.parseInt(source.getId()));
        assertThat(source.getAge()).isEqualTo(dest.getAge(), withPrecision(0.2d));
        assertThat(dest.isActive()).isTrue( );
        System.out.println("dest= " + dest);
    }

}
