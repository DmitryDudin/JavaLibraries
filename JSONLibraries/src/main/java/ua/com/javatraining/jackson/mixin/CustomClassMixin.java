package ua.com.javatraining.jackson.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomClassMixin {
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String privateField;

}
