package ua.com.javatraining.jackson.mixin;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomClass {
    private Long id;
    private String name;
    private String password;
    private String privateField;
}
