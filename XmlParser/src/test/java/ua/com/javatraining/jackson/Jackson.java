package ua.com.javatraining.jackson;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.util.List;

public class Jackson {

    @Test
    public void test() throws Exception {
        JAXBContext context = JAXBContext.newInstance(Jackson.XmlFail.class);

        ObjectMapper objectMapper = new XmlMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDefaultMergeable(true);
        objectMapper.registerModule(new JaxbAnnotationModule());

        String xml =
                "<foo>" +
                    "<items>" +
                        "<double>2.0</double>" +
                        "<integer>1</integer>" +
                    "</items>" +
                "</foo>";

        Jackson.XmlFail unmarshal = (Jackson.XmlFail) context.createUnmarshaller().unmarshal(new ByteArrayInputStream(xml.getBytes()));
        System.out.println(unmarshal.getClass() + " " + unmarshal.items);

        XmlFail xmlFail = objectMapper.readValue(xml, XmlFail.class);
        System.out.println(xmlFail.items);
    }

    //    @JacksonXmlRootElement(localName = "foo")
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
