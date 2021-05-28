package ua.com.javatraining;

import com.viridiansoftware.java.ClassFile;
import com.viridiansoftware.java.utils.ClassUtils;
import org.junit.Test;

import java.io.IOException;

public class ViridiansoftwareJavaClassParser {

    @Test
    public void name() throws IOException {
        final ClassFile classFile = new ClassFile(getClass().getResourceAsStream("TestClassForParsing.class"));
//        System.out.println(classFile.getThisSignature());
//        System.out.println(classFile.toString());
        System.out.println(classFile.getSourceFile());
    }

    @Test
    public void name2() {
        final String referenceClass = ClassUtils.getReferenceClass("java/lang/Object");
        System.out.println(referenceClass);
    }

}
