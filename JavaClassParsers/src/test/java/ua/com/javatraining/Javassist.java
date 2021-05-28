package ua.com.javatraining;

import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Javassist {

    @Test
    public void name() {
        try {
            ClassPool cp = ClassPool.getDefault();
            ClassFile cf = cp.get("java.lang.Object").getClassFile();
            cf.write(new DataOutputStream(new FileOutputStream(
            "D:\\projects\\archive\\JavaLibraries\\JavaClassParsers\\/Object111.class")));
        } catch (NotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}
