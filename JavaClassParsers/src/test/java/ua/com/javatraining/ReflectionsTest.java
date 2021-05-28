package ua.com.javatraining;

import org.junit.Test;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.serializers.JavaCodeSerializer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class ReflectionsTest {

    @Test
    public void name() throws IOException, ClassNotFoundException {
        Reflections reflections = new Reflections(TestClassForParsing.class);
        System.out.println(reflections.toString());
//        String str = reflections.;

//        reflections.save("D:\\projects\\archive\\JavaLibraries\\JavaClassParsers\\/ReflectionsTestFile.txt");
        reflections.save("D:\\projects\\archive\\JavaLibraries\\JavaClassParsers\\/ReflectionsTestJava",
                new JavaCodeSerializer());
//        final Class<?> aClass = JavaCodeSerializer.resolveClass(TestClassForParsing.class);
//        final Class<?> aClass = JavaCodeSerializer.resolveClassOf(TestClassForParsing.class);
//        System.out.println(aClass);

//        System.out.println(new JavaCodeSerializer().toString(reflections));
    }


    @Test
    public void reflectionUtilsTests() {
        final Set<Method> allMethods = ReflectionUtils.getAllMethods(TestClassForParsing.class);
        System.out.println(allMethods);
    }
}
