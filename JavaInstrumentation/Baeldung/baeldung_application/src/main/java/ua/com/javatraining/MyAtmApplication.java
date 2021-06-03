package ua.com.javatraining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class MyAtmApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(MyAtmApplication.class);

    public static void run(String[] args) throws Exception {
        AgentLoader.run(args);

        LOGGER.info("[Application] Starting ATM application");

//        Thread.sleep(Long.valueOf(args[1]));
        TimeUnit.SECONDS.sleep(1);

        MyAtm.withdrawMoney(Integer.parseInt(args[2]));

//        Thread.sleep(Long.valueOf(args[1]));
        TimeUnit.SECONDS.sleep(1);

        MyAtm.withdrawMoney(Integer.parseInt(args[3]));
    }


}
