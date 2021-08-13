package ua.com.javatraining;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;


public class SaveBytecodeAgent {

    private static Logger LOGGER = LoggerFactory.getLogger(SaveBytecodeAgent.class);

    public static void premain(String agentArgs, Instrumentation inst) {
        LOGGER.info("agentArgs:\\n {}", agentArgs);
        LOGGER.info("[Agent] In premain method");

//        String className = "com.baeldung.instrumentation.application.MyAtm";
        String className = "ua.com.javatraining.MyAtm";
        transformClass(className, inst);
    }

    public static void agentmain(String agentArgs, Instrumentation inst) {
        LOGGER.info("agentArgs:\\n {}", agentArgs);
        LOGGER.info("[Agent] In agentmain method");

//        String className = "com.baeldung.instrumentation.application.MyAtm";
        String className = "ua.com.javatraining.MyAtm";

        transformClass(className, inst);
    }

    private static void transformClass(String className, Instrumentation instrumentation) {
        LOGGER.info("come in transformClass() ...");

        Class<?> targetCls = null;
        ClassLoader targetClassLoader = null;
        // see if we can get the class using forName
        try {
            targetCls = Class.forName(className);
            targetClassLoader = targetCls.getClassLoader();
            transform(targetCls, targetClassLoader, instrumentation);
            return;
        } catch (Exception ex) {
            LOGGER.error("Class [{}] not found with Class.forName", className);
        }
        // otherwise iterate all loaded classes and find what we want
        LOGGER.error("otherwise iterate all loaded classes and find what we want");
        for (Class<?> clazz : instrumentation.getAllLoadedClasses()) {
            if (clazz.getName().equals(className)) {
                LOGGER.error("Found clazz with name = {}", clazz.getCanonicalName());
                targetCls = clazz;
                targetClassLoader = targetCls.getClassLoader();
                transform(targetCls, targetClassLoader, instrumentation);
                LOGGER.error("OK return from transformClass");
                return;
            }
        }
        throw new RuntimeException("Failed to find class [" + className + "]");
    }

    private static void transform(Class<?> clazz, ClassLoader classLoader, Instrumentation instrumentation) {
        LOGGER.info("come in transform() ...");

        SaveBytecodeTransformer dt = new SaveBytecodeTransformer(clazz.getName(), classLoader);
        LOGGER.info("come in transform() -------2-----------...");

        instrumentation.addTransformer(dt, true);
        LOGGER.info("come in transform() -------3-----------...");


        try {
            instrumentation.retransformClasses(clazz);
        } catch (Exception ex) {
            LOGGER.error("retransformClasses failed for class {}", clazz.getName());
            throw new RuntimeException("Transform failed for class: [" + clazz.getName() + "]", ex);
        }
        LOGGER.info("return  transform() ...");
    }

}
