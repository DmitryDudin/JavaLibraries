package ua.com.javatraining.jackson.simpleGitHub;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new XmlMapper();
//        objectMapper.enableDefaultTyping();
//        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerSubtypes(AudioAttachment.class);
        objectMapper.registerSubtypes(Audio.class);
        objectMapper.registerSubtypes(LinkAttachment.class);
        objectMapper.registerSubtypes(Link.class);
        objectMapper.registerSubtypes(Attachment.class);

        /*String xml =
                "<foo>" +
                        "<items>" +
                        "<double>2.0</double>" +
                        "<integer>1</integer>" +
                        "</items>" +
                        "</foo>";*/

        String xml =
                "<foo>" +
                        "<items>" +
                        "<audio>" +
                            "<id>11</id>" +

//                            "<id>" +
//                        "<aid>11</aid>" +
//                        "<ownerId>11</ownerId>" +
//                        "</id>" +

                        "<aid>11</aid>" +
                        "<ownerId>11</ownerId>" +
                        "</audio>" +
                        "<link>" +

                            "<id>22</id>" +

                        "<url>cusomUrl</url>" +
                        "<title>cusomTitle</title>" +
                        "</link>" +
                        "</items>" +
                        "</foo>";

        String xml2 =
                "<root>" +
                        "<attachments>" +
                        "<audio>" +
                        "<aid>11</aid>" +
                        "<ownerId>11</ownerId>" +
                        "</audio>" +
                        "<link>" +
                        "<url>cusomUrl</url>" +
                        "<title>cusomTitle</title>" +
                        "</link>" +
                        "</attachments>" +
                        "</root>";

//        RootClass rootClass = objectMapper.readValue(xml2, RootClass.class);
//        System.out.println(rootClass);

        XmlFail xmlFail = objectMapper.readValue(xml, XmlFail.class);
        System.out.println(xmlFail);
    }

    @ToString
    @JacksonXmlRootElement(localName = "foo")
    public static class XmlFail {

//        @XmlElementWrapper(name = "items")
//        @XmlElements({
//                @XmlElement(name = "double", type = Double.class),
//                @XmlElement(name = "integer", type = Integer.class)
//        })

        /*@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
        @JsonSubTypes({
                @JsonSubTypes.Type(value = Audio.class, name = "audio"),
                @JsonSubTypes.Type(value = Link.class, name = "link")
        })*/
        /*@JacksonXmlElementWrapper(useWrapping = false)
        @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
        @JsonSubTypes({
                @JsonSubTypes.Type(value = Audio.class, name = "audio"),
                @JsonSubTypes.Type(value = Link.class, name = "link")
        })*/


        @JacksonXmlProperty(localName = "items")
//        @JacksonXmlElementWrapper(useWrapping = true)
//        @JacksonXmlElementWrapper(useWrapping = false)
//        private List<Attachment> items;
        private AttachmentList items;
    }

    /*@ToString//working
    @JacksonXmlRootElement(localName = "foo")
    public static class XmlFail {

//        @XmlElementWrapper(name = "items")
//        @XmlElements({
//                @XmlElement(name = "double", type = Double.class),
//                @XmlElement(name = "integer", type = Integer.class)
//        })

        @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "type")
        @JsonSubTypes({
                @JsonSubTypes.Type(value = Double.class, name = "double"),
                @JsonSubTypes.Type(value = Integer.class, name = "integer")
        })
        private List<Number> items;
    }*/
}
