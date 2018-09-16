package ua.com.javatraining.jackson.simpleGitHub;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//@JsonTypeName("link")
public class Link extends Attachment {
    public String url;
    public String title;
}
