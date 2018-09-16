package ua.com.javatraining.jackson.simpleGitHub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "root")
public class RootClass {

    @JacksonXmlProperty(localName = "attachments")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "clazz")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Audio.class, name = "audio"),
            @JsonSubTypes.Type(value = Link.class, name = "link")
    })
    private List<Attachment> attachments;

    @JsonProperty
    private String clazz = this.getClass().getSimpleName();
}
