package ua.com.javatraining;

import org.junit.Test;
import ua.com.javatraining.domain.User;

public class GenericClassBuilder {

    @Test
    public void test() {
        CommonResponse.<User>builder()
                .data(new User(123L))
                .build();
    }

    @Test
    public void name() {
        User.builder()
                .id(123L)
                .build();
    }
}
