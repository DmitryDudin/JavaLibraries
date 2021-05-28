package ua.com.javatraining;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class RoasterTests {

    @Test
    public void roaster1() {
        JavaClassSource javaClassSource = Roaster.parse(JavaClassSource.class, "public class HelloWorld {}");
        System.out.println(javaClassSource.toString());
//        String str = javaClassSource.
    }

    @Test
    public void roaster2() throws FileNotFoundException {

        final JavaClassSource javaClassSource = Roaster.parse(
                JavaClassSource.class,
                getClass().getResourceAsStream("/TestClassForParsing.java")
        );
        System.out.println(javaClassSource.toString());
    }
}
