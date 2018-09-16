package ua.com.javatraining.jackson.simpleGitHub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkAttachment extends Attachment {

//    public String url;
//    public String title;

    public Link link;
}
