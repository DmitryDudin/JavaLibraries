package ua.com.javatraining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by adi on 6/11/18.
 */
public class MyAtm {
    private static Logger LOGGER = LoggerFactory.getLogger(MyAtm.class);

    private static final int account = 10;

    public static void withdrawMoney(int amount) throws InterruptedException {
        Thread.sleep(1500l); //processing going on here
        LOGGER.info("[Application] Successful Withdrawal of [{}] units!", amount);
    }

}
