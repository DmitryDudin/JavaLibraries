package ua.com.javatraining.jackson.simpleGitHub;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AudioAttachment extends Attachment {

//    public int aid;
//    public int ownerId;

    public Audio audio;
}
