package ua.com.javatraining;

import org.apache.commons.crypto.utils.ReflectionUtils;
import org.junit.Test;

public class ApacheReflectionUtils {

    @Test
    public void getClassByName() throws ClassNotFoundException {
        final Class<?> classByName = ReflectionUtils.getClassByName("ua.com.javatraining.TestClassForParsing");
        System.out.println(classByName.getCanonicalName());
    }

}
