package ua.com.javatraining.lang3;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class Contains {

    @Test
    public void name2() {
        String[] strArr = new String[]{"str", "asdf", "asdf"};

        System.out.println(StringUtils.containsAny("str", strArr));
    }

}
