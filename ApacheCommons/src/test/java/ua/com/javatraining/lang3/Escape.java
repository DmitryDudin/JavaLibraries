package ua.com.javatraining.lang3;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class Escape {
    @Test
    public void name() {
//        org.apache.commons.text.StringEscapeUtils - new!!!

        System.out.println("1  " + StringEscapeUtils.unescapeXml("<category>CATEGORY.LEISURE.PARTY</category>"));
        System.out.println("1  " + StringEscapeUtils.unescapeXml("&lt;category&gt;CATEGORY.LEISURE.PARTY&lt;/category&gt;"));
        System.out.println("2  " + StringEscapeUtils.escapeXml("<category>CATEGORY.LEISURE.PARTY</category>"));
        System.out.println("3  " + StringEscapeUtils.unescapeHtml4("<category>CATEGORY.LEISURE.PARTY</category>"));
        System.out.println("4  " + StringEscapeUtils.escapeHtml4("<div class=\"grid ps-relative\">"));
        System.out.println("4  " + StringEscapeUtils.unescapeHtml4(" &lt;div class=&quot;grid ps-relative&quot;&gt;"));
    }


}
