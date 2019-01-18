package ua.com.javatraining.domain;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String name;

    @Builder
    public User(Long id) {
        this.id = id;
    }

}
