package ua.com.javatraining.jackson.jacksonPolymorphicDeserialization;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cat extends Animal {

    boolean likesCream;
    public int lives;

    public Cat(String name, Boolean likesCream, Integer lives) {
        super(name);
        this.likesCream = likesCream;
        this.lives = lives;
    }

}