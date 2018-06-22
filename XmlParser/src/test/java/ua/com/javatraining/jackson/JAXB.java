package ua.com.javatraining.jackson;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.util.List;

public class JAXB {

    @Test
    public void test() throws Exception {
        JAXBContext context = JAXBContext.newInstance(XmlFail.class);

        String xml =
                "<foo>" +
                    "<items>" +
                        "<double>2.0</double>" +
                        "<integer>1</integer>" +
                    "</items>" +
                "</foo>";

        XmlFail unmarshal = (XmlFail) context.createUnmarshaller().unmarshal(new ByteArrayInputStream(xml.getBytes()));
        System.out.println(unmarshal.getClass() + " " + unmarshal.items);
    }

    @XmlRootElement(name = "foo")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class XmlFail {

        @XmlElementWrapper(name = "items")
        @XmlElements({
                @XmlElement(name = "double", type = Double.class),
                @XmlElement(name = "integer", type = Integer.class)
        })
        private List<Number> items;
    }

}
