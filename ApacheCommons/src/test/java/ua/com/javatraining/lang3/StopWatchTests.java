package ua.com.javatraining.lang3;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class StopWatchTests {

    @Test
    public void name() throws InterruptedException {
        StopWatch total = StopWatch.createStarted();
//        log.info("product_etl: done in {} ms", total.getTime());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(total.getTime());
    }

}
