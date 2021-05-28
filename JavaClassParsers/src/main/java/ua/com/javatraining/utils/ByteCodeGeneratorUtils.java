package ua.com.javatraining.utils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;

import java.io.IOException;

public class ByteCodeGeneratorUtils {

    public static void getByteCode(String classCanonicalName) throws NotFoundException, CannotCompileException, IOException {
        ClassPool
                .getDefault()
                .get( classCanonicalName)
                .writeFile(System.getProperty("user.dir")+"/GeneratedByteCode");
    }

}
