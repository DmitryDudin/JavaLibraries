package ua.com.javatraining;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ByteCodeGeneratorUtils {

    public static void javassistGetByteCode(String classCanonicalName) {
        try {
            ClassPool
                    .getDefault()
                    .get(classCanonicalName)
                    .writeFile(System.getProperty("user.dir") + "/GeneratedByteCode");
        } catch (CannotCompileException | IOException | NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void asmGetByteCode(String classCanonicalName) {
        final String[] nameParts = classCanonicalName.split("\\.");
        String fileName = System.getProperty("user.dir")
                + "/ForTestFiles/ASMTests/" + nameParts[nameParts.length - 1] + ".class";

        try {
            ClassReader reader = new ClassReader(classCanonicalName);

            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            reader.accept(classWriter, 0);

            Path path = Paths.get(fileName);
            Files.write(path, classWriter.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
