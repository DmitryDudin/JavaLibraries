package ua.com.javatraining.jackson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.annotation.*;
import java.io.ByteArrayInputStream;
import java.util.List;

@XmlRootElement(name = "foo")
@XmlAccessorType(XmlAccessType.FIELD)
public class JAXB {

    @XmlElementWrapper(name = "items")
    @XmlElements({
            @XmlElement(name = "double", type = Double.class),
            @XmlElement(name = "integer", type = Integer.class)
    })
    private List<Number> items;

//  when child elements having various names
    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(JAXB.class);

        String xml =
                "<foo>" +
                    "<items>" +
                        "<double>2.0</double>" +
                        "<integer>1</integer>" +
                    "</items>" +
                "</foo>";

        JAXB unmarshal = (JAXB) context.createUnmarshaller().unmarshal(new ByteArrayInputStream(xml.getBytes()));
        System.out.println(unmarshal.getClass() + " " + unmarshal.items);
    }

}
