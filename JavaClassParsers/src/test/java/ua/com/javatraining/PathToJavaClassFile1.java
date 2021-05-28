package ua.com.javatraining;

import org.junit.Test;

public class PathToJavaClassFile1 {

    @Test
    public void getPath() {
//        System.out.println(TestClassForParsing.class.getProtectionDomain().getCodeSource().toString());
        System.out.println(TestClassForParsing.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println(TestClassForParsing.class.getResource("TestClassForParsing.class"));
    }

}
