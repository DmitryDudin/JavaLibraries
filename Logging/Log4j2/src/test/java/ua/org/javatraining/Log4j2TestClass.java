package ua.org.javatraining;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

//https://logging.apache.org/log4j/2.x/manual/customconfig.html
public class Log4j2TestClass {
    Logger LOG = LogManager.getLogger(Log4j2TestClass.class);

    @Test
    public void simpleTest(){
//        LOG.info("info");
        LOG.error("error message");//work without file config
    }

    @Test
    public void logInfoTest(){
        LOG.debug("minus one debug message");
        LOG.warn("zero warn message");
        LOG.info("first info message");
        LOG.error("second error message");
        LOG.fatal("second fatal message");
    }

}
