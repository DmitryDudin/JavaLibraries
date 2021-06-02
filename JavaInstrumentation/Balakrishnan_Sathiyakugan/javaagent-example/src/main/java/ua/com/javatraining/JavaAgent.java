package ua.com.javatraining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.TimeUnit;

public class JavaAgent {

//todo      1.check without package structure

    private static Logger LOGGER = LoggerFactory.getLogger(JavaAgent.class);


    /**
     * As soon as the JVM initializes, This  method will be called.
     * Configs for intercepting will be read and added to Transformer so that Transformer will intercept when the
     * corresponding Java Class and Method is loaded.
     *
     * @param agentArgs       The list of agent arguments
     * @param instrumentation The instrumentation object
     * @throws InstantiationException While  an instantiation of object cause an error.
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) throws InstantiationException, InterruptedException {
//we can(!!!!!!!!!) debug here if line (-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005)  goes first

        LOGGER.info("agentArgs:\\n {}", agentArgs);
        LOGGER.info("Starting Java Agent......");
//        TimeUnit.SECONDS.sleep(30);//for remote debug

        InterceptingClassTransformer interceptingClassTransformer = new InterceptingClassTransformer();
        interceptingClassTransformer.init();
        instrumentation.addTransformer(interceptingClassTransformer);

        LOGGER.info("end premain......");
//        TimeUnit.SECONDS.sleep(30);//for remote debug
    }

}