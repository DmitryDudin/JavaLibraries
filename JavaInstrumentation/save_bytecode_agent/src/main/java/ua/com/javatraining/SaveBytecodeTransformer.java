package ua.com.javatraining;


import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.LoaderClassPath;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class SaveBytecodeTransformer implements ClassFileTransformer {

    private static Logger LOGGER = LoggerFactory.getLogger(SaveBytecodeTransformer.class);

    private static final String WITHDRAW_MONEY_METHOD = "withdrawMoney";

    /**
     * The internal form class name of the class to transform
     */
    private String targetClassName;
    /**
     * The class loader of the class we want to transform
     */
    private ClassLoader targetClassLoader;

    public SaveBytecodeTransformer(String targetClassName, ClassLoader targetClassLoader) {
        this.targetClassName = targetClassName;
        this.targetClassLoader = targetClassLoader;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) {

//        LOGGER.info("Strt trasfor"); Nicobu$04Nicobu$04
        byte[] byteCode = classfileBuffer;
        String finalTargetClassName = this.targetClassName.replaceAll("\\.", "/");
        if (!className.equals(finalTargetClassName)) {
            return byteCode;
        }

        if (className.equals(finalTargetClassName) && loader.equals(targetClassLoader)) {

            LOGGER.info("[Agent] Transforming class MyAtm");
            try {//[class path: <null>;]
                ClassPool classPool = ClassPool.getDefault();//[class path: jdk.internal.loader.ClassLoaders$AppClassLoader@1d44bcfa;]
                classPool.insertClassPath(new LoaderClassPath(targetClassLoader));
                CtClass ctClass = classPool.get(targetClassName);

                CtMethod ctMethod = ctClass.getDeclaredMethod(WITHDRAW_MONEY_METHOD);
                ctMethod.addLocalVariable("startTime", CtClass.longType);
                ctMethod.insertBefore("startTime = System.currentTimeMillis();");

                StringBuilder endBlock = new StringBuilder();

                ctMethod.addLocalVariable("endTime", CtClass.longType);
                ctMethod.addLocalVariable("opTime", CtClass.longType);
                endBlock.append("endTime = System.currentTimeMillis();");
                endBlock.append("opTime = (endTime-startTime)/1000;");

                endBlock.append("LOGGER.info(\"[TRANSFORMED________________________________Application] Withdrawal operation completed in:" + "\" + opTime + \" seconds!\");");

                ctMethod.insertAfter(endBlock.toString());

                byteCode = ctClass.toBytecode();
                ctClass.detach();
            } catch (NotFoundException | CannotCompileException | IOException e) {
                LOGGER.error("Exception", e);
            }
        }
        return byteCode;
    }

}
