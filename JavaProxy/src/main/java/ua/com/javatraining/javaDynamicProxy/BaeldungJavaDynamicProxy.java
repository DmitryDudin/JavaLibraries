package ua.com.javatraining.javaDynamicProxy;

import ua.com.javatraining.ByteCodeGeneratorUtils;

import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class BaeldungJavaDynamicProxy {

//getClass().getClassLoader()

    public static void main(String[] args) throws ClassNotFoundException {
        Map proxyInstance = (Map) Proxy
                .newProxyInstance(
                        BaeldungJavaDynamicProxy.class.getClassLoader(),
                        new Class[]{Map.class},
                        new DynamicInvocationHandler()
                );

        System.out.println(proxyInstance.put("hello", "world"));
        System.out.println(proxyInstance.get("hello"));

        final Class<?> aClass = Class.forName(proxyInstance.getClass().getCanonicalName());
//        aClass.getResourceAsStream(aClass.getCanonicalName());//null
//        final InputStream resourceAsStream = BaeldungJavaDynamicProxy.class.getResourceAsStream("/com/sun/proxy/$Proxy0.class");//null
//        final InputStream resourceAsStream = proxyInstance.getClass().getResourceAsStream(aClass.getCanonicalName());//null

//        ByteCodeGeneratorUtils.javassistGetByteCode(proxyInstance.getClass().getCanonicalName());
//        ByteCodeGeneratorUtils.asmGetByteCode(proxyInstance.getClass().getCanonicalName());//java.io.IOException: Class not found

//        final InputStream resourceAsStream = proxyInstance.getClass().getClassLoader().getResourceAsStream(proxyInstance.getClass().getCanonicalName());
//        int i =9;
    }


    public static class DynamicInvocationHandler implements InvocationHandler {
        //        private static Logger LOGGER = LoggerFactory.getLogger(DynamicInvocationHandler.class);
        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
//            LOGGER.info("Invoked method: {}", method.getName());
            System.out.println("Invoked method: " + method.getName());
            return 42;
        }
    }

}
