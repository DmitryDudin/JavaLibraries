package ua.com.javatraining;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.util.TraceClassVisitor;
import ua.com.javatraining.utils.ByteCodeGeneratorUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ASMTests______________________WORKING {

    @Test
    public void name() throws IOException {
        final String canonicalClassName = "java.lang.Object";
        final String[] nameParts = canonicalClassName.split("\\.");
        String fileName = System.getProperty("user.dir") + "/ForTestFiles/ASMTests/" +
                nameParts[nameParts.length - 1] /*+ ".class"*/;

        ClassReader reader = new ClassReader(canonicalClassName);

        StringWriter stringWriter = new StringWriter();
//        TraceClassVisitor tcv = new TraceClassVisitor(new PrintWriter(System.out));
//        TraceClassVisitor tcv = new TraceClassVisitor(new PrintWriter(stringWriter));
        TraceClassVisitor tcv = new TraceClassVisitor(new PrintWriter(new FileWriter(fileName)));


//        final FileWriter fileWriter = new FileWriter(System.getProperty("user.dir")+"/ForTestFiles/ASMTests");
//        String fileName = System.getProperty("user.dir")+"/ForTestFiles/ASMTests/TargetClass";
//        TraceClassVisitor tcv = new TraceClassVisitor(new PrintWriter(fileName));

        reader.accept(tcv, 0);

//        final FileWriter fileWriter = new FileWriter(fileName);
//        fileWriter.write(stringWriter.toString());
//        fileWriter.close();

//        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
//        out.write(stringWriter.toString());
//        out.close();
    }

    @Test
    public void name2_______________WORKING() throws IOException {
//        final String canonicalClassName = "java.lang.Object";
        final String canonicalClassName = TestClassForParsing.class.getCanonicalName();
        final String[] nameParts = canonicalClassName.split("\\.");
        String fileName = System.getProperty("user.dir")
                + "/ForTestFiles/ASMTests/" + nameParts[nameParts.length - 1] + ".class";

        ClassReader reader = new ClassReader(canonicalClassName);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        reader.accept(classWriter, 0);

        Path path = Paths.get(fileName);
        Files.write(path, classWriter.toByteArray());
    }

    @Test
    public void testUtilMethod() {
        ByteCodeGeneratorUtils.asmGetByteCode(String.class.getCanonicalName());
    }

}
