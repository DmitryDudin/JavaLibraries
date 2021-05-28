package ua.com.javatraining;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.junit.Test;

import java.io.IOException;

public class ApacheCommonsBytecodeEngineeringLibrary {

    @Test
    public void printByteCode() throws ClassNotFoundException {
        TestClassForParsing testObjectForParsing = new TestClassForParsing();

        final Class<? extends TestClassForParsing> testClassForParsingGetClass = testObjectForParsing.getClass();
        final Class<TestClassForParsing> testClassForParsingClass = TestClassForParsing.class;

        JavaClass objectClazz = Repository.lookupClass("java.lang.Object");
        JavaClass javaClassTestClassForParsingClass = Repository.lookupClass(testClassForParsingGetClass);
        JavaClass javaClassTestClassForParsingGetClass = Repository.lookupClass(testClassForParsingClass);

        System.out.println(objectClazz.toString());
    }


    @Test
    public void printBaseInfo() throws IOException {
        ClassParser classParser = new ClassParser("D:\\projects\\archive\\JavaLibraries\\JavaClassParsers\\build\\classes\\java\\main\\ua\\com\\javatraining\\TestClassForParsing.class");
        final JavaClass parse = classParser.parse();
        System.out.println(parse.toString());

    }

}
