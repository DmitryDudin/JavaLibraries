package ua.com.javatraining;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.Bytecode;
import javassist.compiler.Javac;
import org.junit.Test;
import ua.com.javatraining.utils.ByteCodeGeneratorUtils;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;

public class Javassist__________________WORKING {
//Javassist

//Getting Started with Javassist
//https://www.javassist.org/tutorial/tutorial.html

//Основы Javassist и как использовать Javassist для достижения АОП
//https://russianblogs.com/article/5140211219/ (!!!!!!!!!!!!!!!!!!!!!!!!!!!!!)

    @Test
    public void getByteCodeByCLassNmae() throws NotFoundException, IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
//        final CtClass ctClass = pool.get("java.lang.Object");
        final TestClassForParsing testClassForParsingObj = new TestClassForParsing();
        final String canonicalName = testClassForParsingObj.getClass().getCanonicalName();
//        final String canonicalName = TestClassForParsing.class.getCanonicalName();
//        final CtClass ctClass = pool.get("ua.com.javatraining.TestClassForParsing");
        final CtClass ctClass = pool.get(canonicalName);

        //todo byteCode!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        ctClass.writeFile("D:\\projects\\archive\\JavaLibraries\\JavaClassParsers\\ForTestFiles\\JavaC");
        ClassPool
                .getDefault()
                .get( testClassForParsingObj.getClass().getCanonicalName())
                .writeFile(System.getProperty("user.dir")+"/ForTestFiles/JavaC");

        String str = new String();
        ByteCodeGeneratorUtils
                .javassistGetByteCode(str.getClass().getCanonicalName());

//        ClassPool
//                .getDefault()
//                .insertClassPath(new ClassClassPath(String.class));

        final Javac javac = new Javac(ctClass);
        final Bytecode bytecode = javac.getBytecode();
    }

    @Test
    public void name2() {
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
    }

    @Test
    public void name3() {
//        CachedCompiler cc = new CachedCompiler(null, null);
    }
}
