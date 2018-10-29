package ua.com.javatraining.jackson.mixin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class JacksonMixinTest {

//    Mix-ins allow us to apply behavior (such as ignoring properties when serializing and deserializing)
//    without the need to directly apply annotations to a class.

    @Test
    public void test() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.addMixIn(CustomClass.class, CustomClassMixin.class);

        CustomClass customClass = new CustomClass(111l, "username", "password", "privateValue");
        String jsonCustomClass = objectMapper.writeValueAsString(customClass);
        System.out.println(jsonCustomClass);//{"id":111,"name":"username"}
    }

}
